package com.example.cooperativa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooperativa.utils.Cypher;
import com.example.cooperativa.utils.DataPreferences;
import com.example.cooperativa.utils.RecoverStep1;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity implements TextWatcher, View.OnKeyListener, View.OnFocusChangeListener {

    String TAG = "LoginActicity";
    Button btnAcceder, btnValidatePIN;
    TextView pswRecover;
    EditText et_digit1,et_digit2,et_digit3, et_digit4, etIdentification, etPassword;
    FrameLayout container;
    View inflatedLayout, inflatedLayout2, inflatedLayout3;
    ImageView imgCloseStep2;
    Spinner spnIdentificacion;
    RadioButton rbRemember;
    int whoHasFocus;
    char[] code = new char[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        spnIdentificacion = (Spinner)findViewById(R.id.spnIdentificacion);
        etIdentification = (EditText)findViewById(R.id.etIdentification);
        etPassword = (EditText)findViewById(R.id.etPassword);
        btnAcceder = (Button) findViewById(R.id.btnAcceder);
        rbRemember = (RadioButton) findViewById(R.id.rbRemember);
        container = (FrameLayout) findViewById(R.id.flContainer);
        pswRecover = (TextView) findViewById(R.id.pswRecover);

        if(new DataPreferences(getApplicationContext()).getBool("Remember"))
        {
            try {
                spnIdentificacion.setSelection(new DataPreferences(getApplicationContext()).getInt("Position"));
                etIdentification.setText(new Cypher().DecodeBase64(new DataPreferences(getApplicationContext()).getStr("Identification")));
                etPassword.setText(new Cypher().DecodeBase64(new DataPreferences(getApplicationContext()).getStr("Password")));
            }
            catch (UnsupportedEncodingException uex)
            {
                Log.e(TAG,uex.toString());
            }
        }

        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(rbRemember.isChecked())
                    {
                        new DataPreferences(getApplicationContext()).setBool("Remember",true);
                        new DataPreferences(getApplicationContext()).setInt("Position",spnIdentificacion.getSelectedItemPosition());
                        new DataPreferences(getApplicationContext()).setStr("Identification",new Cypher().EncodeBase64(etIdentification.getText().toString()));
                        new DataPreferences(getApplicationContext()).setStr("Password",new Cypher().EncodeBase64(etPassword.getText().toString()));
                    }
                }
                catch (UnsupportedEncodingException uex)
                {
                    Log.e(TAG,uex.toString());
                }



                String[] typeID = getResources().getStringArray(R.array.arrIdentification);
                //startActivity(new Intent(LoginActivity.this, MainActivity.class));
                if(spnIdentificacion.getSelectedItem().toString().equals(typeID[0]))
                {
                    ////Use Cedula Authentication
                    if(etIdentification.getText().toString().replace("-","").equals("40224063863") && etPassword.getText().toString().equals("jjhb0432"))
                    {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(getString(R.string.done))
                                .setContentText(getString(R.string.access_granted))
                                .setConfirmText(getString(R.string.action_access))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                                    }
                                })
                                .show();
                    }
                    else
                    {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getString(R.string.try_again))
                                .setContentText(getString(R.string.couldnt_access))
                                .show();
                    }
                }
                else if(spnIdentificacion.getSelectedItem().toString().replace("-","").equals(typeID[1]))
                {
                    ////Use Codigo Authentication
                    if(etIdentification.getText().toString().equals("23815") && etPassword.getText().toString().equals("jjhb0432"))
                    {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText(getString(R.string.done))
                                .setContentText(getString(R.string.access_granted))
                                .setConfirmText(getString(R.string.action_access))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                                    }
                                })
                                .show();
                    }
                    else
                    {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getString(R.string.try_again))
                                .setContentText(getString(R.string.couldnt_access))
                                .show();
                    }
                }
            }
        });

        pswRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflatedLayout = getLayoutInflater().inflate(R.layout.forgotten_step_1, null, false);
                Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                inflatedLayout.setAnimation(slide_up);
                container.addView(inflatedLayout);

                ImageView imgCloseStep1 = (ImageView) inflatedLayout.findViewById(R.id.imgCloseStep1);
                imgCloseStep1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        container.removeAllViews();
                    }
                });

                EditText etEmailForgotten = (EditText) inflatedLayout.findViewById(R.id.etEmailForgotten);

                Button btnContinue = (Button) inflatedLayout.findViewById(R.id.btnContinue);
                btnContinue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(isValidEmail(etEmailForgotten.getText())) {
                            new DataPreferences(getApplicationContext()).setStr("email",etEmailForgotten.getText().toString());
                            container.removeAllViews();
                            container = (FrameLayout) findViewById(R.id.flContainer);
                            inflatedLayout2 = getLayoutInflater().inflate(R.layout.forgotten_step_2, null, false);
                            Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                            inflatedLayout2.setAnimation(slide_up);
                            container.addView(inflatedLayout2);
                            initializeView(inflatedLayout2);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.invalidEmail),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        // Do something
    }

    private void initializeView(View view)
    {
        et_digit1 = (EditText) view.findViewById(R.id.et_vfcode_digit1);
        et_digit2 = (EditText) view.findViewById(R.id.et_vfcode_digit2);
        et_digit3 = (EditText) view.findViewById(R.id.et_vfcode_digit3);
        et_digit4 = (EditText) view.findViewById(R.id.et_vfcode_digit4);
        imgCloseStep2 = (ImageView)view.findViewById(R.id.imgCloseStep2);

        imgCloseStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container.removeAllViews();
            }
        });

        btnValidatePIN = (Button)view.findViewById(R.id.btnValidatePIN);
        btnValidatePIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pin = String.valueOf(code);
                if(Pin.replace(" ","").length() < 4)
                {
                    Toast.makeText(getApplicationContext(),getString(R.string.not_valid),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(Pin.equals("0432"))
                    {
                        container.removeAllViews();
                        startActivity(new Intent(LoginActivity.this, ForgetActivity.class).putExtra("code", Pin));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),getString(R.string.not_validate),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        setListners();
    }

    private void setListners()
    {
        et_digit1.addTextChangedListener(this);
        et_digit2.addTextChangedListener(this);
        et_digit3.addTextChangedListener(this);
        et_digit4.addTextChangedListener(this);

        et_digit1.setOnKeyListener(this);
        et_digit2.setOnKeyListener(this);
        et_digit3.setOnKeyListener(this);
        et_digit4.setOnKeyListener(this);

        et_digit1.setOnFocusChangeListener(this);
        et_digit2.setOnFocusChangeListener(this);
        et_digit3.setOnFocusChangeListener(this);
        et_digit4.setOnFocusChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        switch (whoHasFocus)
        {
            case 1:
                if(!et_digit1.getText().toString().isEmpty())
                {
                    code[0]= et_digit1.getText().toString().charAt(0);
                    et_digit2.requestFocus();
                }
                break;

            case 2:
                if(!et_digit2.getText().toString().isEmpty())
                {
                    code[1]= et_digit2.getText().toString().charAt(0);
                    et_digit3.requestFocus();
                }
                break;

            case 3:
                if(!et_digit3.getText().toString().isEmpty())
                {
                    code[2]= et_digit3.getText().toString().charAt(0);
                    et_digit4.requestFocus();
                }
                break;

            case 4:
                if(!et_digit4.getText().toString().isEmpty())
                {
                    code[3]= et_digit4.getText().toString().charAt(0);
                }
                break;


            default:
                break;
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN)
            {
                if (i == KeyEvent.KEYCODE_DEL)
                {
                    switch(view.getId())
                    {
                        case R.id.et_vfcode_digit2:
                            if (et_digit2.getText().toString().isEmpty())
                                et_digit1.requestFocus();
                            break;

                        case R.id.et_vfcode_digit3:
                            if (et_digit3.getText().toString().isEmpty())
                                et_digit2.requestFocus();
                            break;

                        case R.id.et_vfcode_digit4:
                            if (et_digit4.getText().toString().isEmpty())
                                et_digit3.requestFocus();
                            break;

                        default:
                            break;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.et_vfcode_digit1:
                whoHasFocus = 1;
                break;

            case R.id.et_vfcode_digit2:
                whoHasFocus = 2;
                break;

            case R.id.et_vfcode_digit3:
                whoHasFocus = 3;
                break;

            case R.id.et_vfcode_digit4:
                whoHasFocus = 4;
                break;

            default:
                break;
        }
    }
}

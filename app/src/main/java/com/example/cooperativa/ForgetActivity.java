package com.example.cooperativa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cooperativa.utils.DataPreferences;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgetActivity extends AppCompatActivity {
    String code, email;
    EditText etPswForgotten, etRetyoe;
    TextView charCounter1, charCounter2;
    Button btnRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        this.getSupportActionBar().hide();

        btnRestore = (Button)findViewById(R.id.btnRestore);
        etPswForgotten = (EditText) findViewById(R.id.etPswForgotten);
        charCounter1 = (TextView)findViewById(R.id.charCounter1);
        etRetyoe=(EditText) findViewById(R.id.etRetyoe);
        charCounter2 = (TextView)findViewById(R.id.charCounter2);

        etPswForgotten.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = etPswForgotten.getText().toString().length()+" "+ getString(R.string.characters);
                charCounter1.setText(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etRetyoe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = etRetyoe.getText().toString().length()+" "+ getString(R.string.characters);
                charCounter2.setText(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (getIntent().getExtras() != null) {
            code = getIntent().getStringExtra("code");
            email = new DataPreferences(getApplicationContext()).getStr("email");
        }

        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etRetyoe.getText().toString().length() >= 8)
                {
                    if(etRetyoe.getText().toString().equals(etPswForgotten.getText().toString()))
                    {
                        new SweetAlertDialog(ForgetActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Estas seguro?")
                                .setContentText("Esta sera tu nueva contraseña")
                                .setConfirmText(getString(R.string.si))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.setTitleText(getString(R.string.done))
                                                .setContentText(getString(R.string.pogress_completed))
                                                .setConfirmText(getString(R.string.si))
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        startActivity(new Intent(ForgetActivity.this, LoginActivity.class));
                                                    }
                                                })
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    }

                                })
                                .setCancelButton(getString(R.string.no), new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                    }
                                })
                                .show();
                    }
                    else
                    {
                        new SweetAlertDialog(ForgetActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText(getString(R.string.try_again))
                                .setContentText(getString(R.string.not_equal_passwords))
                                .show();
                    }
                }
                else
                {
                    new SweetAlertDialog(ForgetActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(getString(R.string.try_again))
                            .setContentText(getString(R.string.major_than_8))
                            .show();
                }

            }
        });

    }
}
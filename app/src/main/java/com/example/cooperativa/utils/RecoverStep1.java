package com.example.cooperativa.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cooperativa.R;

public class RecoverStep1 extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes;
    public ImageView no;

    public RecoverStep1(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.forgotten_step_1);

        yes = (Button) findViewById(R.id.btnContinue);
        no = (ImageView) findViewById(R.id.imgCloseStep1);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnContinue:
                c.finish();
                break;
            case R.id.imgCloseStep1:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

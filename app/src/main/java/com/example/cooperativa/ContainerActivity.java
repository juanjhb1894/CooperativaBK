package com.example.cooperativa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ContainerActivity extends AppCompatActivity {

    ImageView imgPrestamos, imgAhorros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        this.getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, new HomeFragment());
        ft.commit();

        imgPrestamos = (ImageView) findViewById(R.id.imgPrestamos);
        imgPrestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContainerActivity.this, PrestamosActivity.class));
            }
        });

        imgAhorros = (ImageView) findViewById(R.id.imgAhorros);
        imgAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContainerActivity.this, AhorrosActivity.class));
            }
        });
    }
}
package com.example.cooperativa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PrestamosActivity extends AppCompatActivity {

    ImageView imgPrestamosHome, imgCalcular, imgHistoryPrestamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);

        this.getSupportActionBar().hide();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerPrestamos, new CalculadoraPrestamosFragment());
        ft.commit();

        imgPrestamosHome = (ImageView) findViewById(R.id.imgPrestamosHome);
        imgCalcular = (ImageView) findViewById(R.id.imgCalcular);
        imgHistoryPrestamos = (ImageView) findViewById(R.id.imgHistoryPrestamos);

        imgPrestamosHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrestamosActivity.this, ContainerActivity.class));
            }
        });

        imgCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containerPrestamos, new CalculadoraPrestamosFragment());
                ft.commit();
            }
        });

        imgHistoryPrestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.containerPrestamos, new HistorialPrestamosFragment());
                ft.commit();
            }
        });

    }
}
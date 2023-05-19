package com.example.cooperativa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.cooperativa.adapters.AhorrosDataAdapter;
import com.example.cooperativa.adapters.HitorialPrestamosAdapter;
import com.example.cooperativa.adapters.ProductRecyclerViewAdapter;
import com.example.cooperativa.models.AhorrosModel;
import com.example.cooperativa.models.HistorialPrestamosModel;
import com.example.cooperativa.models.ProductsModel;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AhorrosActivity extends AppCompatActivity {

    ListView lstMovimientosLite;
    ImageView imgAhorroHome, imgAhorroPrestamos;
    ArrayList<AhorrosModel> dataModels;
    ArrayList<ProductsModel> productos;
    AhorrosDataAdapter adapter;
    RecyclerView rvProducts;
    private ProductRecyclerViewAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahorros);

        this.getSupportActionBar().hide();

        lstMovimientosLite = (ListView)findViewById(R.id.lstMovimientosLite);
        imgAhorroHome = (ImageView)findViewById(R.id.imgAhorroHome);
        imgAhorroPrestamos = (ImageView)findViewById(R.id.imgAhorroPrestamos);
        rvProducts = (RecyclerView) findViewById(R.id.lstProductos);

        productos = new ArrayList<>();
            StringBuilder jsonProducts = new StringBuilder();
            BufferedReader productsReader = null;
            try {
                productsReader = new BufferedReader(new InputStreamReader(getAssets().open("products.json")));
                // do reading, usually loop until end of file reading
                String mLine="";
                while ((mLine = productsReader.readLine()) != null) {
                    //process line
                    jsonProducts.append(mLine);
                }

                JSONArray jsonArray = new JSONArray(jsonProducts.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    // Create a marker for each city in the JSON data.
                    JSONObject jsonObj = jsonArray.getJSONObject(i);

                    productos.add(new ProductsModel(jsonObj.getInt("id"),
                            jsonObj.getInt("estado"),
                            jsonObj.getString("tipo"),
                            jsonObj.getString("total"),
                            jsonObj.getString("pendiente"),
                            jsonObj.getString("solicitado_el")));
                }
            }
            catch (Exception ex)
            {}

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(AhorrosActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rvProducts.setLayoutManager(horizontalLayoutManager);
        pAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), productos);
        //adapter.setClickListener(this);
        rvProducts.setAdapter(pAdapter);


        imgAhorroHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AhorrosActivity.this, ContainerActivity.class));
            }
        });

        imgAhorroPrestamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AhorrosActivity.this, PrestamosActivity.class));
            }
        });

        dataModels= new ArrayList<>();
        StringBuilder json = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("movimientos.json")));
            // do reading, usually loop until end of file reading
            String mLine="";
            while ((mLine = reader.readLine()) != null) {
                //process line
                json.append(mLine);
            }

            JSONArray jsonArray = new JSONArray(json.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                // Create a marker for each city in the JSON data.
                JSONObject jsonObj = jsonArray.getJSONObject(i);

                dataModels.add(new AhorrosModel(jsonObj.getInt("id"),
                        jsonObj.getString("nombre"),
                        jsonObj.getString("tipo"),
                        jsonObj.getString("monto"),
                        jsonObj.getString("fecha")));
            }

            adapter= new AhorrosDataAdapter(dataModels, getApplicationContext());
            lstMovimientosLite.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

    }



}
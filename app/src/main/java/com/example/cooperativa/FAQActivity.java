package com.example.cooperativa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cooperativa.adapters.FAQDataAdapter;
import com.example.cooperativa.models.FAQModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    EditText FAQSearchBar;
    ImageView imgFAQHome, imgFAQAhorros, imgFAQPrestamo, imgCloseFAQ;
    TextView tvFAQTitulo, tvFAQContenido;
    ListView lvFAQ;
    List<FAQModel> faqModels;
    FAQDataAdapter adapter;
    View inflatedLayout;
    FrameLayout container;
    boolean isFAQShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        this.getSupportActionBar().hide();

        FAQSearchBar = (EditText) findViewById(R.id.FAQSearchBar);
        imgFAQHome =(ImageView) findViewById(R.id.imgFAQHome);
        imgFAQAhorros =(ImageView) findViewById(R.id.imgFAQAhorros);
        imgFAQPrestamo =(ImageView) findViewById(R.id.imgFAQPrestamo);
        container = (FrameLayout) findViewById(R.id.flFAQContainer);

        lvFAQ = (ListView) findViewById(R.id.lstFAQ);
        lvFAQ.setTextFilterEnabled(true);

        imgFAQHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFAQShown) {
                    startActivity(new Intent(FAQActivity.this, ContainerActivity.class));
                }
            }
        });

        imgFAQAhorros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFAQShown) {
                    startActivity(new Intent(FAQActivity.this, AhorrosActivity.class));
                }
            }
        });

        imgFAQPrestamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFAQShown) {
                    startActivity(new Intent(FAQActivity.this, PrestamosActivity.class));
                }
            }
        });

        faqModels = new ArrayList<>();
        StringBuilder json = new StringBuilder();
        BufferedReader productsReader = null;
        try {
            productsReader = new BufferedReader(new InputStreamReader(getAssets().open("faq.json")));
            // do reading, usually loop until end of file reading
            String mLine="";
            while ((mLine = productsReader.readLine()) != null) {
                //process line
                json.append(mLine);
            }

            JSONArray jsonArray = new JSONArray(json.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                // Create a marker for each city in the JSON data.
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                faqModels.add(new FAQModel(jsonObj.getInt("id"),
                        jsonObj.getString("titulo"),
                        jsonObj.getString("contenido"),
                        jsonObj.getString("creado_el")));
            }

            adapter= new FAQDataAdapter(faqModels, getApplicationContext());
            lvFAQ.setAdapter(adapter);
        }
        catch (Exception ex)
        {
            Log.e("Error", ex.toString());
        }

        FAQSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                //FAQActivity.this.adapter.getFilter().filter(arg0);
                if(FAQSearchBar.getText().toString().length()>=3)
                {
                    adapter.clear();
                    lvFAQ.setAdapter(adapter);
                    BufferedReader productReader = null;
                    try {
                        productReader = new BufferedReader(new InputStreamReader(getAssets().open("faq.json")));
                        // do reading, usually loop until end of file reading
                        String mLine = "";
                        while ((mLine = productReader.readLine()) != null) {
                            //process line
                            json.append(mLine);
                        }

                        JSONArray jsonArray = new JSONArray(json.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            // Create a marker for each city in the JSON data.
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            if(jsonObj.getString("titulo").toLowerCase().contains(arg0.toString().toLowerCase()) ||
                                    jsonObj.getString("contenido").toLowerCase().contains(arg0.toString().toLowerCase()))
                            {
                                faqModels.add(new FAQModel(jsonObj.getInt("id"),
                                        jsonObj.getString("titulo"),
                                        jsonObj.getString("contenido"),
                                        jsonObj.getString("creado_el")));
                            }
                        }
                        adapter = new FAQDataAdapter(faqModels, getApplicationContext());
                        lvFAQ.setAdapter(adapter);
                    }
                    catch (Exception ex)
                    {
                        Log.e("Error", ex.toString());
                    }
                }
                else if(FAQSearchBar.getText().toString().length()==0)
                {
                    adapter.clear();
                    lvFAQ.setAdapter(adapter);
                    StringBuilder json = new StringBuilder();
                    BufferedReader productsReader = null;
                    try {
                        productsReader = new BufferedReader(new InputStreamReader(getAssets().open("faq.json")));
                        // do reading, usually loop until end of file reading
                        String mLine="";
                        while ((mLine = productsReader.readLine()) != null) {
                            //process line
                            json.append(mLine);
                        }

                        JSONArray jsonArray = new JSONArray(json.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            // Create a marker for each city in the JSON data.
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            faqModels.add(new FAQModel(jsonObj.getInt("id"),
                                    jsonObj.getString("titulo"),
                                    jsonObj.getString("contenido"),
                                    jsonObj.getString("creado_el")));
                        }

                        adapter= new FAQDataAdapter(faqModels, getApplicationContext());
                        lvFAQ.setAdapter(adapter);
                    }
                    catch (Exception ex)
                    {
                        Log.e("Error", ex.toString());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        lvFAQ.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FAQModel faq = (FAQModel) faqModels.toArray()[i];

                if(!isFAQShown)
                {
                    isFAQShown = true;
                    FAQSearchBar.setEnabled(false);
                    lvFAQ.setEnabled(false);

                    inflatedLayout = getLayoutInflater().inflate(R.layout.faq_container, null, false);
                    Animation fadeIn = new AlphaAnimation(0, 1);
                    fadeIn.setInterpolator(new DecelerateInterpolator());
                    fadeIn.setDuration(1000);

                    AnimationSet animation = new AnimationSet(false);
                    animation.addAnimation(fadeIn);
                    inflatedLayout.setAnimation(animation);
                    container.addView(inflatedLayout);

                    imgCloseFAQ = (ImageView) inflatedLayout.findViewById(R.id.imgCloseFAQ);
                    tvFAQTitulo = (TextView) inflatedLayout.findViewById(R.id.tvFAQTitulo);
                    tvFAQContenido = (TextView) inflatedLayout.findViewById(R.id.tvFAQContenido);

                    tvFAQTitulo.setText(faq.getTitulo());
                    tvFAQContenido.setText(faq.getContenido());

                    imgCloseFAQ.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FAQSearchBar.setEnabled(true);
                            lvFAQ.setEnabled(true);
                            isFAQShown = false;
                            container.removeAllViews();
                        }
                    });
                }



            }
        });
    }


}
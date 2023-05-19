package com.example.cooperativa.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cooperativa.R;
import com.example.cooperativa.utils.ImageDownloader;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private static final String TAG = "CustomInfoWindowAdapter";
        private LayoutInflater inflater;
        private Context context;

        public CustomInfoWindowAdapter(LayoutInflater inflater, Context context){
            this.inflater = inflater;
            this.context = context;
        }

        Bitmap bitmap  = null;
        Drawable drawable = null;
        @Override
        public View getInfoContents(final Marker m) {
            //Carga layout personalizado.
            View v = inflater.inflate(R.layout.infowindow_layout, null);
            try {
                JSONObject jObj = new JSONObject(m.getSnippet());

                ((TextView)v.findViewById(R.id.tvName)).setText(jObj.getString("name"));
                ((TextView)v.findViewById(R.id.tvAddress)).setText(jObj.getString("address"));
                ((TextView)v.findViewById(R.id.tvPhone)).setText(jObj.getString("phone"));
                ((TextView)v.findViewById(R.id.tvPortal)).setText("https://coopnaseju.com.do/");

                //Glide.with(context).load(jObj.getString("image")).into(((ImageView)v.findViewById(R.id.info_window_imagen)));

                if (android.os.Build.VERSION.SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                }

                URL url = new URL(jObj.getString("image"));
                Bitmap myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                Log.e("keshav", "Bitmap " + myBitmap);
                ((ImageView)v.findViewById(R.id.info_window_imagen)).setImageBitmap(myBitmap);

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return v;
        }

        @Override
        public View getInfoWindow(Marker m) {
            return null;
        }

}

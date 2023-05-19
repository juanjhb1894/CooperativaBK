package com.example.cooperativa.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cooperativa.R;
import com.example.cooperativa.models.AhorrosModel;

import java.util.ArrayList;

public class AhorrosDataAdapter extends ArrayAdapter<AhorrosModel> implements View.OnClickListener{

        private ArrayList<AhorrosModel> dataSet;
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            TextView MoviLiteTitulo;
            TextView MoviLiteValor;
            ImageView MoviLiteIcon;
        }

        public AhorrosDataAdapter(ArrayList<AhorrosModel> data, Context context) {
            super(context, R.layout.item_prestamo, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            AhorrosModel dataModel=(AhorrosModel) object;

            switch (v.getId())
            {

            }
        }

        private int lastPosition = -1;

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            AhorrosModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_movimiento_lite, parent, false);
                viewHolder.MoviLiteTitulo = (TextView) convertView.findViewById(R.id.MoviLiteTitulo);
                viewHolder.MoviLiteValor = (TextView) convertView.findViewById(R.id.MoviLiteValor);
                viewHolder.MoviLiteIcon = (ImageView) convertView.findViewById(R.id.MoviLiteIcon);

                result=convertView;
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }
            lastPosition = position;

            viewHolder.MoviLiteTitulo.setText(dataModel.getNombre());
            viewHolder.MoviLiteValor.setText(dataModel.getMonto());

            switch (dataModel.getTipo().toLowerCase())
            {
                case "salida":
                    viewHolder.MoviLiteIcon.setImageResource(R.drawable.ic_sent);
                    break;
                case "entrada":
                    viewHolder.MoviLiteIcon.setImageResource(R.drawable.ic_received);
                    break;
            }
            // Return the completed view to render on screen
            return convertView;
        }
}



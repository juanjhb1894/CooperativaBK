package com.example.cooperativa.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.cooperativa.R;
import com.example.cooperativa.models.FAQModel;
import java.util.List;

public class FAQDataAdapter extends ArrayAdapter<FAQModel> implements View.OnClickListener, Filterable {

        private List<FAQModel> dataSet;
        Context mContext;


    // View lookup cache
        private static class ViewHolder {
            TextView FAQTitulo;
            TextView FAQContenido;
        }

        public FAQDataAdapter(List<FAQModel> data, Context context) {
            super(context, R.layout.item_prestamo, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            FAQModel dataModel=(FAQModel) object;

            switch (v.getId())
            {

            }
        }

        private int lastPosition = -1;

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            FAQModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());

                convertView = inflater.inflate(R.layout.item_faq, parent, false);

                viewHolder.FAQTitulo = (TextView) convertView.findViewById(R.id.FAQTitulo);
                viewHolder.FAQContenido = (TextView) convertView.findViewById(R.id.FAQContenido);

                result=convertView;
                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }
            lastPosition = position;

            viewHolder.FAQTitulo.setText(dataModel.getTitulo());
            ////permitir solo 140 caracteres en el contenido
            viewHolder.FAQContenido.setText(new StringBuilder().append(dataModel.getContenido().substring(0, 100).replace("\n", "")).append("...").toString());
            // Return the completed view to render on screen
            return convertView;
        }
}



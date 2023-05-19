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
import com.example.cooperativa.models.HistorialPrestamosModel;

import java.util.ArrayList;

public class HitorialPrestamosAdapter  extends ArrayAdapter<HistorialPrestamosModel> implements View.OnClickListener{

        private ArrayList<HistorialPrestamosModel> dataSet;
        Context mContext;

        // View lookup cache
        private static class ViewHolder {
            TextView txtTipo;
            TextView txtFecha;
            TextView txtEstado;
            TextView txtMonto;
            ImageView imgIcon;
        }

        public HitorialPrestamosAdapter(ArrayList<HistorialPrestamosModel> data, Context context) {
            super(context, R.layout.item_prestamo, data);
            this.dataSet = data;
            this.mContext=context;

        }

        @Override
        public void onClick(View v) {

            int position=(Integer) v.getTag();
            Object object= getItem(position);
            HistorialPrestamosModel dataModel=(HistorialPrestamosModel)object;

            switch (v.getId())
            {

            }
        }

        private int lastPosition = -1;

        @SuppressLint("UseCompatLoadingForDrawables")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            HistorialPrestamosModel dataModel = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            ViewHolder viewHolder; // view lookup cache stored in tag

            final View result;

            if (convertView == null) {

                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.item_prestamo, parent, false);
                viewHolder.txtTipo = (TextView) convertView.findViewById(R.id.prestamoTitulo);
                viewHolder.txtEstado = (TextView) convertView.findViewById(R.id.prestamoEstado);
                viewHolder.txtFecha = (TextView) convertView.findViewById(R.id.prestamoFecha);
                viewHolder.txtMonto= (TextView) convertView.findViewById(R.id.prestamoMonto);
                viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.prestamoIcon);

                result=convertView;

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                result=convertView;
            }
            lastPosition = position;

            viewHolder.txtTipo.setText(dataModel.getTipo());
            viewHolder.txtFecha.setText(dataModel.getFecha());
            viewHolder.txtMonto.setText(dataModel.getNomto());

            switch (dataModel.getEstado())
            {
                case "1":
                    viewHolder.txtEstado.setText(mContext.getString(R.string.solicitado));
                    viewHolder.txtEstado.setBackground(mContext.getDrawable(R.drawable.prestamo_solicitado));
                    break;
                case "2":
                    viewHolder.txtEstado.setText(mContext.getString(R.string.pendiente));
                    viewHolder.txtEstado.setBackground(mContext.getDrawable(R.drawable.prestamo_pendiente));
                    break;
                case "3":
                    viewHolder.txtEstado.setText(mContext.getString(R.string.rechazado));
                    viewHolder.txtEstado.setBackground(mContext.getDrawable(R.drawable.prestamo_rechazado));
                    break;
                case "4":
                    viewHolder.txtEstado.setText(mContext.getString(R.string.aprobado));
                    viewHolder.txtEstado.setBackground(mContext.getDrawable(R.drawable.prestamo_aprobado));
                    break;
            }
            // Return the completed view to render on screen
            return convertView;
        }
}



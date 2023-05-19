package com.example.cooperativa.models;

public class HistorialPrestamosModel {

        String tipo;
        String estado;
        String monto;
        String fecha;

        public HistorialPrestamosModel(String type, String status, String amount, String date ) {
            this.tipo=type;
            this.estado=status;
            this.monto=amount;
            this.fecha=date;

        }

        public String getTipo() {
            return tipo;
        }

        public String getEstado() {
            return estado;
        }

        public String getNomto() {
            return monto;
        }

        public String getFecha() {
            return fecha;
        }

}

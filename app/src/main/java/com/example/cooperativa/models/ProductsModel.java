package com.example.cooperativa.models;

public class ProductsModel {

        int id;
        int estado;
        String tipo;
        String total;
        String pendiente;
        String fecha;

        public ProductsModel(int id, int status, String type, String amount,  String debt, String date ) {
            this.id=id;
            this.estado=status;
            this.tipo=type;
            this.total=amount;
            this.pendiente=debt;
            this.fecha=date;

        }

        public int  getId() {
        return id;
    }

        public int getEstado() {
        return estado;
    }

        public String getTipo() {
        return tipo;
    }

        public String getMonto() {
            return total;
        }

        public String getPendiente() {
        return pendiente;
    }

        public String getFecha() {
            return fecha;
        }

}

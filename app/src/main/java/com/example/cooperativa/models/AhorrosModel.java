package com.example.cooperativa.models;

public class AhorrosModel {

        int id;
        String nombre;
        String tipo;
        String monto;
        String fecha;

        public AhorrosModel(int id, String name, String type, String amount, String date ) {
            this.id=id;
            this.nombre=name;
            this.tipo=type;
            this.monto=amount;
            this.fecha=date;

        }

        public int  getid() {
        return id;
    }

        public String getNombre() {
        return nombre;
    }

        public String getTipo() {
        return tipo;
    }

        public String getMonto() {
            return monto;
        }

        public String getFecha() {
            return fecha;
        }

}

package com.example.cooperativa.models;

public class FAQModel {

        int id;
        String titulo;
        String contenido;
        String fecha;

        public FAQModel(int id, String title, String content,String date ) {
            this.id=id;
            this.titulo=title;
            this.contenido=content;
            this.fecha=date;

        }

        public int  getId() {
        return id;
    }

        public String getTitulo() {
        return titulo;
    }

        public String getContenido() {
        return contenido;
    }

        public String getFecha() {
            return fecha;
        }

}

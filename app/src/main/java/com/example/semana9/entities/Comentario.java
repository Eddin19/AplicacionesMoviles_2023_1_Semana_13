package com.example.semana9.entities;

public class Comentario {
    public int id;
    private String contenido;

    public Comentario(int id, String contenido) {
        this.id = id;
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}


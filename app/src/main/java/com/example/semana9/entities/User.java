package com.example.semana9.entities;


import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class User {
    public int id;
    public String descripcion;
    public String imagenUrl;
    public LatLng ubicacion;
    private List<Comentario> comentarios;
    public User(String descripcion, String imagenUrl, LatLng ubicacion) {
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.ubicacion = ubicacion;
        this.comentarios = new ArrayList<>();
    }

    public User(String descripcion) {
        this.descripcion = descripcion;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public LatLng getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(LatLng ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }
}
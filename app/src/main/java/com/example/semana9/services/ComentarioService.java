package com.example.semana9.services;

import com.example.semana9.entities.Comentario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ComentarioService {
    @GET("comentarios")
    Call<List<Comentario>> getAllComentario();

    @PUT("users/{id}/comentarios")
    Call<Comentario> updateComentario(@Path("id") int id, @Body Comentario comentario);
}

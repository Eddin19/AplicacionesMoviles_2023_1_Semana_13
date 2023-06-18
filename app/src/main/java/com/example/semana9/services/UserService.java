package com.example.semana9.services;

import com.example.semana9.entities.Comentario;
import com.example.semana9.entities.User;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService{
    @GET("users")
    Call<List<User>> getAllUser();

    @GET("users/{id}")
    Call<User> findUser(@Path("id") int id);

    @GET("coordenadas") // Reemplaza esto con el endpoint de tu API Mock
    Call<User> enviarCoordenadas(@Query("location") LatLng location);
    @POST("users")
    Call<User> create(@Body User users);

    @POST("users/{id}/comentarios")
    Call<List<Comentario>> createComentario(@Path("id") int id, @Body Comentario contenido);

    @PUT("users/{id}")
    Call<User> update(@Path("id") int id, @Body User users);

    @DELETE("users/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("image")
    Call<ImageResponse> saveImage(@Body ImageToSave image);

    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image) {
            this.base64Image = base64Image;
        }
    }
}
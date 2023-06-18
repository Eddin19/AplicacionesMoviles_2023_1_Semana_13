package com.example.semana9.ui.theme;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana9.R;
import com.example.semana9.adapters.ComentarioAdapter;
import com.example.semana9.entities.Comentario;
import com.example.semana9.services.ComentarioService;
import com.example.semana9.services.UserService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaComentariosActivity extends AppCompatActivity {

    private RecyclerView rvLista;
    private ComentarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_comentarios);

        rvLista = findViewById(R.id.rvListaSimple);
        rvLista.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ComentarioService service = retrofit.create(ComentarioService.class);
        Call<List<Comentario>> call = service.getAllComentario();

        call.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                Log.i("AM_APP", String.valueOf(response.code()));

                if (response.isSuccessful()) {
                    List<Comentario> comentarios = response.body();

                    Log.i("AM_APP", String.valueOf(comentarios.size()));
                    Log.i("AM_APP", new Gson().toJson(comentarios));

                    adapter = new ComentarioAdapter(comentarios, ListaComentariosActivity.this);
                    rvLista.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {
                // Maneja el error de acuerdo a tus necesidades
            }
        });

    }

    public void actualizarComentario(Comentario comentario) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<List<Comentario>> call = service.createComentario(comentario.id, comentario);

        call.enqueue(new Callback<List<Comentario>>() {
            @Override
            public void onResponse(Call<List<Comentario>> call, Response<List<Comentario>> response) {
                if (response.isSuccessful()) {
                    // El contacto se actualizó exitosamente en la API
                    List<Comentario> contactoActualizado = response.body();
                    Log.d("API", "Actualización exitosa. Contacto actualizado: " + contactoActualizado.toString());
                    // Realiza las acciones necesarias después de actualizar el contacto
                } else {
                    // Hubo un error al actualizar el contacto
                    int statusCode = response.code(); // Obtiene el código de respuesta HTTP
                    Log.e("API", "Error en la respuesta de la API. Código de respuesta: " + statusCode);
                    // Maneja el error de acuerdo a tus necesidades
                }
            }

            @Override
            public void onFailure(Call<List<Comentario>> call, Throwable t) {
                // Hubo un error en la solicitud o en la comunicación con la API
                // Maneja el error de acuerdo a tus necesidades
            }
        });
    }

}
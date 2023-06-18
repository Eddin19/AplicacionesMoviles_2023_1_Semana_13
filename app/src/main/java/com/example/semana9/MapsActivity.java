package com.example.semana9;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.semana9.entities.User;
import com.example.semana9.services.UserService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        userId = intent.getIntExtra("id", 0);

        // Inicializar el mapa asíncronamente
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        obtenerUbicacion();
    }

    private void obtenerUbicacion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477a0a19233e82dd53bf49a.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<User> call = service.findUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    LatLng ubicacion = user.getUbicacion();
                    String nombrePaisaje = user.getDescripcion();

                    // Mostrar el marcador en la ubicación deseada
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(ubicacion).title(nombrePaisaje));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 12f));
                } else {
                    // Error al obtener la ubicación
                    // Maneja el error según tus necesidades
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Error de red o de la API
                // Maneja el error según tus necesidades
            }
        });
    }
}

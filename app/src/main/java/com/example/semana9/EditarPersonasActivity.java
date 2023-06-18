package com.example.semana9;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class EditarPersonasActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText editTextDescripcion;
    private EditText editTextComentario;
    String urlImage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_personas);

        // Obtén las referencias de los elementos de la interfaz de usuario
        editTextDescripcion = findViewById(R.id.tvName);
        imageView = findViewById(R.id.imageView);

        String imagenUrl = "https://demo-upn.bit2bittest.com" + urlImage;

        // Carga la imagen utilizando Picasso
        Picasso.get().load(imagenUrl).into(imageView);

        // Obtén los datos pasados desde la actividad anterior
        String nombre = getIntent().getStringExtra("nombre");

        // Establece los valores en los campos de texto
        editTextDescripcion.setText(nombre);
    }

}

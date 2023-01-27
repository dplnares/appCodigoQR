package com.example.escanerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListarDatosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);

        this.setTitle("Datos del Equipo");
        // Opción para regresar a la ventana anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
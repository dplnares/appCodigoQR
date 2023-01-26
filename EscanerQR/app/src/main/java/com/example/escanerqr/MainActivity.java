package com.example.escanerqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btnScan;
    EditText txtResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        txtResultado = findViewById(R.id.txtResultado);

        // Leer el codigo QR
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
                integrador.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrador.setPrompt("LECTOR - CDP");
                // Camara posterior
                integrador.setCameraId(0);
                integrador.setBeepEnabled(true);
                integrador.setBarcodeImageEnabled(true);
                integrador.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null)
        {
            // SI ES NULO, ES PORQUE SE CANCELÓ, SINO DEVUELVE EL VALOR
            if(result.getContents() == null)
            {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                txtResultado.setText(result.getContents());
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
package com.example.escanerqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarActivity extends AppCompatActivity {

    Button btnAgregarRecurso, btnGenerarQR;
    EditText codigoEquipo, nombreRecurso, nombreProcesador, personalaCargo;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        this.setTitle("Agregar un Equipo");
        // Opción para regresar a la ventana anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        codigoEquipo = findViewById(R.id.txtCodigo);
        nombreRecurso = findViewById(R.id.txtRecurso);
        nombreProcesador = findViewById(R.id.txtProcesador);
        personalaCargo = findViewById(R.id.txtPersonaCargo);

        btnAgregarRecurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = codigoEquipo.getText().toString();
                String recurso = nombreRecurso.getText().toString();
                String procesador = nombreProcesador.getText().toString();
                String personal = personalaCargo.getText().toString();
                
                if(codigo.isEmpty() || recurso.isEmpty() || procesador.isEmpty() || personal.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    agregarRecurso(codigo, recurso, procesador, personal);
                }
            }


        });
    }

    private void agregarRecurso(String codigo, String recurso, String procesador, String personal)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("codigo", codigo);
        map.put("recurso", recurso);
        map.put("procesador", procesador);
        map.put("personal", personal);

        mfirestore.collection("equipocpu").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
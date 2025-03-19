package com.example.petapp.features.diet;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp.R;

public class RegistroDietaActivity extends AppCompatActivity {

    private EditText etTipoComida, etCantidad, etHora;
    private Button btnRegistrarDieta;
    private Dieta dieta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_dieta);

        etTipoComida = findViewById(R.id.etTipoComida);
        etCantidad = findViewById(R.id.etCantidad);
        etHora = findViewById(R.id.etHora);
        btnRegistrarDieta = findViewById(R.id.btnRegistrarDieta);

        // Verificar si se recibió una dieta para editar
        dieta = (Dieta) getIntent().getSerializableExtra("dieta");
        if (dieta != null) {
            etTipoComida.setText(dieta.getTipoComida());
            etCantidad.setText(String.valueOf(dieta.getCantidad()));
            etHora.setText(dieta.getHora());
            btnRegistrarDieta.setText("Actualizar Dieta");
        }

        btnRegistrarDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCampos()) {
                    if (dieta == null) {
                        // Crear una nueva dieta y agregarla al repositorio
                        Dieta nuevaDieta = new Dieta(
                                etTipoComida.getText().toString(),
                                Double.parseDouble(etCantidad.getText().toString()),
                                etHora.getText().toString()
                        );
                        DietaRepository.addDieta(nuevaDieta);
                        Toast.makeText(RegistroDietaActivity.this, "Dieta registrada con éxito", Toast.LENGTH_SHORT).show();
                    } else {
                        // Actualizar la dieta existente
                        dieta.setTipoComida(etTipoComida.getText().toString());
                        dieta.setCantidad(Double.parseDouble(etCantidad.getText().toString()));
                        dieta.setHora(etHora.getText().toString());
                        DietaRepository.updateDieta(dieta);
                        Toast.makeText(RegistroDietaActivity.this, "Dieta actualizada con éxito", Toast.LENGTH_SHORT).show();
                    }
                    // Regresar a la lista de dietas
                    startActivity(new Intent(RegistroDietaActivity.this, ListaDietasActivity.class));
                    finish();
                }
            }
        });
    }

    private boolean validarCampos() {
        return !etTipoComida.getText().toString().isEmpty() &&
                !etCantidad.getText().toString().isEmpty() &&
                !etHora.getText().toString().isEmpty();
    }
}

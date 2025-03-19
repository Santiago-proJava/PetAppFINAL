package com.example.petapp.features.diet;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp.R;

import java.util.List;

public class ListaDietasActivity extends AppCompatActivity {

    private ListView lvDietas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dietas);

        lvDietas = findViewById(R.id.lvDietas);
        loadDietas();
    }

    private void loadDietas() {
        List<Dieta> dietas = DietaRepository.getAllDietas();
        if (dietas.isEmpty()) {
            Toast.makeText(this, "No hay dietas registradas", Toast.LENGTH_SHORT).show();
        }
        DietaAdapter adapter = new DietaAdapter(ListaDietasActivity.this, dietas);
        lvDietas.setAdapter(adapter);
    }
}

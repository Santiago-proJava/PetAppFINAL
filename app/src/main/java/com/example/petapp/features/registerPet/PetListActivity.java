package com.example.petapp.features.registerPet;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp.R;

public class PetListActivity extends AppCompatActivity {

    private TextView tvPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        tvPets = findViewById(R.id.tvPets);

        // Recuperar el objeto Pet del Intent
        Pet pet = getIntent().getParcelableExtra("pet");

        if (pet != null) {
            String petInfo = "Nombre: " + pet.getName() + "\n" +
                    "Especie: " + pet.getSpecies() + "\n" +
                    "Raza: " + pet.getBreed() + "\n" +
                    "Edad: " + pet.getAge() + "\n" +
                    "Peso: " + pet.getWeight() + " kg";
            tvPets.setText(petInfo);
        } else {
            tvPets.setText("No se recibieron datos de la mascota.");
        }
    }
}

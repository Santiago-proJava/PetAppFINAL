package com.example.petapp.features.registerPet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp.R;

public class RegisterPetActivity extends AppCompatActivity {

    private EditText etPetName, etSpecies, etBreed, etAge, etWeight;
    private EditText etBirthDate, etVaccines, etAllergies, etChronicDiseases;
    private RadioGroup rgGender;
    private Button btnRegisterPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pet);

        // Inicializar vistas
        etPetName = findViewById(R.id.etPetName);
        etSpecies = findViewById(R.id.etSpecies);
        etBreed = findViewById(R.id.etBreed);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
        rgGender = findViewById(R.id.rgGender);
        etBirthDate = findViewById(R.id.etBirthDate);
        etVaccines = findViewById(R.id.etVaccines);
        etAllergies = findViewById(R.id.etAllergies);
        etChronicDiseases = findViewById(R.id.etChronicDiseases);
        btnRegisterPet = findViewById(R.id.btnRegisterPet);

        btnRegisterPet.setOnClickListener(v -> {
            if (validateFields()) {
                // Crear una nueva mascota con los datos
                Pet pet = new Pet(
                        etPetName.getText().toString(),
                        etSpecies.getText().toString(),
                        etBreed.getText().toString(),
                        Integer.parseInt(etAge.getText().toString()),
                        Double.parseDouble(etWeight.getText().toString())
                );

                // Enviar la mascota a la siguiente pantalla
                Intent intent = new Intent(RegisterPetActivity.this, PetListActivity.class);
                intent.putExtra("pet", pet);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterPetActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateFields() {
        return !etPetName.getText().toString().isEmpty() &&
                !etSpecies.getText().toString().isEmpty() &&
                !etBreed.getText().toString().isEmpty() &&
                !etAge.getText().toString().isEmpty() &&
                !etWeight.getText().toString().isEmpty() &&
                !etBirthDate.getText().toString().isEmpty() &&
                !etVaccines.getText().toString().isEmpty() &&
                !etAllergies.getText().toString().isEmpty() &&
                !etChronicDiseases.getText().toString().isEmpty();
    }
}

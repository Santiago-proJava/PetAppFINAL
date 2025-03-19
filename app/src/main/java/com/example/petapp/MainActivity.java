package com.example.petapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.petapp.features.diet.ListaDietasActivity;
import com.example.petapp.features.medication.MedicamentoActivity;
import com.example.petapp.features.physicalActivityLog.RegistroActividadActivity;
import com.example.petapp.features.registerPet.RegisterPetActivity;
import com.example.petapp.features.vaccines.VaccineActivity;
import com.example.petapp.features.weight.WeightActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnGoRegisterPet;         // Registrar Mascota
    private Button btnGoVaccine;             // Verificar Vacunas
    private Button btnGoWeightControl;       // Control de Peso
    private Button btnGoDietControl;         // Control de Dieta
    private Button btnGoMedication;          // Control de Medicamentos
    private Button btnGoPhysicalActivity;    // Registro de Actividad Física

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar edge-to-edge
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        // Configurar la vista raíz para que respete los insets del sistema
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        // Referencias a los botones
        btnGoRegisterPet = findViewById(R.id.btnGoRegisterPet);
        btnGoVaccine = findViewById(R.id.btnGoVaccine);
        btnGoWeightControl = findViewById(R.id.btnGoWeightControl);
        btnGoDietControl = findViewById(R.id.btnGoDietControl);
        btnGoMedication = findViewById(R.id.btnGoMedication);
        btnGoPhysicalActivity = findViewById(R.id.btnGoPhysicalActivity);

        // Registrar Mascota
        btnGoRegisterPet.setVisibility(View.VISIBLE);
        btnGoRegisterPet.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterPetActivity.class));
        });

        // Verificar Vacunas
        btnGoVaccine.setVisibility(View.VISIBLE);
        btnGoVaccine.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VaccineActivity.class));
        });

        // Control de Peso
        btnGoWeightControl.setVisibility(View.VISIBLE);
        btnGoWeightControl.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WeightActivity.class));
        });

        // Control de Dieta
        btnGoDietControl.setVisibility(View.VISIBLE);
        btnGoDietControl.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ListaDietasActivity.class));
        });

        // Control de Medicamentos
        btnGoMedication.setVisibility(View.VISIBLE);
        btnGoMedication.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MedicamentoActivity.class));
        });

        // Registro de Actividad Física (última historia de usuario)
        btnGoPhysicalActivity.setVisibility(View.VISIBLE);
        btnGoPhysicalActivity.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistroActividadActivity.class));
        });
    }
}

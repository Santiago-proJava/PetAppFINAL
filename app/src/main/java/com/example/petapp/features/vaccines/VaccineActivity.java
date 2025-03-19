
package com.example.petapp.features.vaccines;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petapp.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class VaccineActivity extends AppCompatActivity {

    private EditText petNameEditText;
    private Spinner vaccineSpinner;
    private Button addVaccineButton;
    private ListView vaccineListView;
    private TextView petNameTextView;
    private ArrayList<String> vaccineList;
    private ArrayAdapter<String> vaccineAdapter;
    private Set<String> appliedVaccines;
    private String[] allVaccines = {"Rabia", "Moquillo", "Parvovirus", "Leptospirosis"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        petNameEditText = findViewById(R.id.petNameEditText);
        vaccineSpinner = findViewById(R.id.vaccineSpinner);
        addVaccineButton = findViewById(R.id.addVaccineButton);
        vaccineListView = findViewById(R.id.vaccineListView);
        petNameTextView = findViewById(R.id.petNameTextView);

        vaccineList = new ArrayList<>();
        appliedVaccines = new HashSet<>();
        vaccineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vaccineList);
        vaccineListView.setAdapter(vaccineAdapter);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allVaccines);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vaccineSpinner.setAdapter(spinnerAdapter);

        addVaccineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String petName = petNameEditText.getText().toString();
                String vaccine = vaccineSpinner.getSelectedItem().toString();
                appliedVaccines.add(vaccine);
                updateVaccineList(petName);
            }
        });

        petNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String petName = petNameEditText.getText().toString();
                    petNameTextView.setText("Nombre de la mascota: " + petName);
                }
            }
        });

        // Inicializa la lista de vacunas
        updateVaccineList("");
    }

    private void updateVaccineList(String petName) {
        vaccineList.clear();
        for (String vaccine : allVaccines) {
            if (appliedVaccines.contains(vaccine)) {
                vaccineList.add(vaccine + " (Aplicada)");
            } else {
                vaccineList.add(vaccine + " (Falta)");
            }
        }
        vaccineAdapter.notifyDataSetChanged();
    }
}

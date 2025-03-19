package com.example.petapp.features.medication;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoActivity extends AppCompatActivity {

    private List<Medicamento> medicamentos;
    private MedicamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamento);

        medicamentos = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMedicamentos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MedicamentoAdapter(this, medicamentos, new MedicamentoAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Medicamento medicamento) {
                showMedicamentoDialog(medicamento.getNombreMascota(), medicamento);
            }

            @Override
            public void onDeleteClick(Medicamento medicamento) {
                deleteMedicamento(medicamento);
            }
        });
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabAgregar = findViewById(R.id.fabAgregar);
        fabAgregar.setOnClickListener(v -> {
            showMedicamentoDialog("Nombre de la mascota", null);
        });
    }

    private void showMedicamentoDialog(String nombreMascota, Medicamento medicamento) {
        new MedicamentoDialog(this, nombreMascota, medicamento, new MedicamentoDialog.MedicamentoDialogListener() {
            @Override
            public void onMedicamentoSaved(Medicamento nuevoMedicamento) {
                if (medicamento != null) {
                    int index = medicamentos.indexOf(medicamento);
                    if (index != -1) {
                        medicamentos.set(index, nuevoMedicamento);
                    }
                } else {
                    // Asignar un ID simple (por ejemplo, tamaño + 1)
                    nuevoMedicamento.setId(medicamentos.size() + 1);
                    medicamentos.add(nuevoMedicamento);
                }
                adapter.updateList(medicamentos);
            }
        }).show();
    }

    private void deleteMedicamento(Medicamento medicamento) {
        int index = medicamentos.indexOf(medicamento);
        if (index != -1) {
            medicamentos.remove(index);
            adapter.updateList(medicamentos);
        }
    }
}


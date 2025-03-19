package com.example.petapp.features.weight;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class WeightActivity extends AppCompatActivity {

    private final List<WeightRecord> records = new ArrayList<>();
    private WeightRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Utiliza el layout renombrado para esta funcionalidad
        setContentView(R.layout.activity_weight);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WeightRecordAdapter(records, record -> showEditDeleteDialog(record));
        recyclerView.setAdapter(adapter);

        Button btnAddRecord = findViewById(R.id.btnAddRecord);
        btnAddRecord.setOnClickListener(v -> showAddRecordDialog());
    }

    private void showAddRecordDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_record, null);
        EditText etWeight = dialogView.findViewById(R.id.etWeight);
        EditText etDate = dialogView.findViewById(R.id.etDate);

        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                etDate.setText(dateFormat.format(selectedDate.getTime()));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        new AlertDialog.Builder(this)
                .setTitle("Agregar Registro")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    double weight = etWeight.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(etWeight.getText().toString());
                    String date = etDate.getText().toString();
                    if (weight > 0 && !date.isEmpty()) {
                        records.add(new WeightRecord(System.currentTimeMillis(), weight, date));
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void showEditDeleteDialog(WeightRecord record) {
        String[] options = {"Editar", "Eliminar"};
        new AlertDialog.Builder(this)
                .setTitle("Selecciona una opción")
                .setItems(options, (dialog, which) -> {
                    switch (which) {
                        case 0:
                            showEditRecordDialog(record);
                            break;
                        case 1:
                            records.remove(record);
                            adapter.notifyDataSetChanged();
                            break;
                    }
                })
                .show();
    }

    private void showEditRecordDialog(WeightRecord record) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_record, null);
        EditText etWeight = dialogView.findViewById(R.id.etWeight);
        EditText etDate = dialogView.findViewById(R.id.etDate);

        etWeight.setText(String.valueOf(record.getWeight()));
        etDate.setText(record.getDate());

        etDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, day) -> {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, day);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                etDate.setText(dateFormat.format(selectedDate.getTime()));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        new AlertDialog.Builder(this)
                .setTitle("Editar Registro")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    double weight = etWeight.getText().toString().isEmpty() ? 0.0 : Double.parseDouble(etWeight.getText().toString());
                    String date = etDate.getText().toString();
                    if (weight > 0 && !date.isEmpty()) {
                        record.setWeight(weight);
                        record.setDate(date);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
}

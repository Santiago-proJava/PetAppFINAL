package com.example.petapp.features.medication;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import com.example.petapp.R;

public class MedicamentoDialog {

    public interface MedicamentoDialogListener {
        void onMedicamentoSaved(Medicamento medicamento);
    }

    private AlertDialog dialog;
    private EditText etNombreMedicamento;
    private Spinner spinnerDosis;
    private Spinner spinnerFrecuencia;
    private Button btnGuardar;

    public MedicamentoDialog(Context context, String nombreMascota, Medicamento medicamento, MedicamentoDialogListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_medicamento, null);
        etNombreMedicamento = view.findViewById(R.id.etNombreMedicamento);
        spinnerDosis = view.findViewById(R.id.spinnerDosis);
        spinnerFrecuencia = view.findViewById(R.id.spinnerFrecuencia);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        // Configurar spinners con opciones de ejemplo
        String[] dosisOptions = {"10mg", "20mg", "30mg"};
        ArrayAdapter<String> adapterDosis = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, dosisOptions);
        adapterDosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDosis.setAdapter(adapterDosis);

        String[] frecuenciaOptions = {"Cada 8 horas", "Cada 12 horas", "Cada 24 horas"};
        ArrayAdapter<String> adapterFrecuencia = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, frecuenciaOptions);
        adapterFrecuencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrecuencia.setAdapter(adapterFrecuencia);

        if (medicamento != null) {
            etNombreMedicamento.setText(medicamento.getNombreMedicamento());
            // Opcionalmente, establecer selección de spinners si coincide
        }

        btnGuardar.setOnClickListener(v -> {
            String nombreMed = etNombreMedicamento.getText().toString().trim();
            String dosis = spinnerDosis.getSelectedItem().toString();
            String frecuencia = spinnerFrecuencia.getSelectedItem().toString();
            Medicamento nuevoMedicamento = new Medicamento(nombreMascota, nombreMed, dosis, frecuencia);
            listener.onMedicamentoSaved(nuevoMedicamento);
            dialog.dismiss();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
    }

    public void show() {
        dialog.show();
    }
}


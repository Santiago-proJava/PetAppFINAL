package com.example.petapp.features.physicalActivityLog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.petapp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RegistroActividadActivity extends AppCompatActivity {

    private EditText etTipoActividad, etDuracion;
    private Button btnGuardar;
    private RecyclerView rvActividades;
    private List<ActividadFisica> actividades;
    private ActividadesAdapter adapter;
    private int contadorId = 1; // Para simular IDs únicos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_actividad);

        etTipoActividad = findViewById(R.id.etTipoActividad);
        etDuracion = findViewById(R.id.etDuracion);
        btnGuardar = findViewById(R.id.btnGuardar);
        rvActividades = findViewById(R.id.rvActividades);

        actividades = new ArrayList<>();
        adapter = new ActividadesAdapter(actividades, new ActividadesAdapter.OnItemClickListener() {
            @Override
            public void onEditar(ActividadFisica actividad) {
                // Para la simulación, al editar se puede rellenar el formulario con los datos de la actividad
                etTipoActividad.setText(actividad.getTipo());
                etDuracion.setText(actividad.getDuracion());
                // Se elimina el registro a editar para simular una edición
                int index = actividades.indexOf(actividad);
                if (index != -1) {
                    actividades.remove(index);
                    adapter.notifyItemRemoved(index);
                }
            }

            @Override
            public void onEliminar(ActividadFisica actividad) {
                int index = actividades.indexOf(actividad);
                if (index != -1) {
                    actividades.remove(index);
                    adapter.notifyItemRemoved(index);
                    Toast.makeText(RegistroActividadActivity.this, "Actividad eliminada", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rvActividades.setLayoutManager(new LinearLayoutManager(this));
        rvActividades.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarActividad();
            }
        });
    }

    private void guardarActividad() {
        String tipo = etTipoActividad.getText().toString().trim();
        String duracion = etDuracion.getText().toString().trim();

        if (!TextUtils.isEmpty(tipo) && !TextUtils.isEmpty(duracion)) {
            String fechaActual = obtenerFechaActual();
            ActividadFisica nuevaActividad = new ActividadFisica(contadorId++, tipo, duracion, fechaActual);
            actividades.add(nuevaActividad);
            adapter.notifyItemInserted(actividades.size() - 1);
            limpiarCampos();
            Toast.makeText(this, "Actividad guardada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        etTipoActividad.setText("");
        etDuracion.setText("");
    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}

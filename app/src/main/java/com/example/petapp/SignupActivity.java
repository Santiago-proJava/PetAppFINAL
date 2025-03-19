package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText etNombreCompleto;
    private TextInputEditText etUsuario;
    private TextInputEditText etEmail;
    private TextInputEditText etDireccion;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private android.widget.Spinner spinnerRoles;
    private android.widget.EditText etFechaNacimiento;
    private android.widget.RadioGroup rgGenero;
    private android.widget.RadioButton rbMasculino;
    private android.widget.RadioButton rbFemenino;
    private android.widget.RadioButton rbBinario;
    private Button btnObtenerUbicacion;
    private Button btnRegistro;

    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Inicializar vistas
        etNombreCompleto = findViewById(R.id.etNombreCompleto);
        etUsuario = findViewById(R.id.etUsuario);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        spinnerRoles = findViewById(R.id.spinnerRoles);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rgGenero = findViewById(R.id.rgGenero);
        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);
        rbBinario = findViewById(R.id.rbBinario);
        btnObtenerUbicacion = findViewById(R.id.btnObtenerUbicacion);
        btnRegistro = findViewById(R.id.btnRegistro);

        // Configurar Spinner con los roles: "Propietario" y "Veterinario"
        String[] roles = {"propietario", "veterinario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                roles
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoles.setAdapter(adapter);

        // Configurar DatePicker para fecha de nacimiento
        etFechaNacimiento.setOnClickListener(view -> mostrarDatePicker());

        // Botón para obtener ubicación (pendiente de implementar lógica de permisos)
        btnObtenerUbicacion.setOnClickListener(view ->
                Toast.makeText(SignupActivity.this, "Funcionalidad de geolocalización pendiente", Toast.LENGTH_SHORT).show());

        // Botón Registrar
        btnRegistro.setOnClickListener(view -> {
            if (validarCampos()) {
                // Guardar el rol seleccionado en SharedPreferences
                String selectedRole = spinnerRoles.getSelectedItem().toString().toLowerCase();
                SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                prefs.edit().putString("role", selectedRole).apply();
                Log.d(TAG, "Rol registrado: " + selectedRole);

                // Aquí podrías guardar los datos del usuario en una BD o enviarlos a un servidor
                Toast.makeText(SignupActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                finish(); // Regresa a la pantalla de Login
            }
        });
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (datePicker, anio, mes, dia) -> {
                    String fechaSeleccionada = dia + "/" + (mes + 1) + "/" + anio;
                    etFechaNacimiento.setText(fechaSeleccionada);
                },
                year,
                month,
                day
        );
        datePickerDialog.show();
    }

    private boolean validarCampos() {
        String nombre = etNombreCompleto.getText().toString().trim();
        String usuario = etUsuario.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String direccion = etDireccion.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();

        if (nombre.isEmpty() || usuario.isEmpty() || email.isEmpty() || direccion.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Aquí podrías agregar validación de la fecha de nacimiento (mayor de 18 años)

        return true;
    }
}


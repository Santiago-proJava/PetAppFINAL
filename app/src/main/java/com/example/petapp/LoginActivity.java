package com.example.petapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private ImageView imgUserIcon;
    private TextInputEditText etUsuarioLogin;
    private TextInputEditText etPasswordLogin;
    private Button btnLogin;
    private Button btnIrRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Referencias a las vistas
        imgUserIcon = findViewById(R.id.imgUserIcon);
        etUsuarioLogin = findViewById(R.id.etUsuarioLogin);
        etPasswordLogin = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);

        // Botón Login
        btnLogin.setOnClickListener(view -> {
            String usuario = etUsuarioLogin.getText().toString().trim();
            String password = etPasswordLogin.getText().toString().trim();

            if (usuario.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Simulamos que el login es exitoso (aquí iría tu lógica de validación real)
                Log.d(TAG, "Intento de inicio de sesión para: " + usuario);

                // Verificar si existe un rol en SharedPreferences
                SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                String userRole = prefs.getString("role", "");
                Log.d(TAG, "Rol obtenido en LoginActivity: " + userRole);

                if (userRole.isEmpty()) {
                    // No hay rol definido, obligamos a registrarse
                    Toast.makeText(LoginActivity.this, "No se ha definido un rol. Regístrate primero.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                } else {
                    // Ya existe un rol en SharedPreferences, permitimos el acceso a MainActivity
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        // Botón para ir a la pantalla de Registro
        btnIrRegistro.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        });
    }
}

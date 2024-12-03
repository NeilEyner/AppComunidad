package com.aplicacion.appcomunidad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aplicacion.appcomunidad.ApiClient;
import com.aplicacion.appcomunidad.ApiService;
import com.aplicacion.appcomunidad.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar las vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        // Inicializar el servicio API
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        // Configurar el listener para el botón de inicio de sesión
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validar campos vacíos
        if (email.isEmpty()) {
            emailEditText.setError("El correo electrónico es requerido");
            emailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordEditText.setError("La contraseña es requerida");
            passwordEditText.requestFocus();
            return;
        }

        // Llamada al API para iniciar sesión
        Call<LoginResponse> call = apiService.login(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if ("success".equals(loginResponse.getStatus())) {
                        // Login exitoso, mostrar un mensaje y redirigir al usuario
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        User user = loginResponse.getData();
                        int rolId = user.getID_Rol();
                        if (rolId == 1) { // Rol de artesano
                            Toast.makeText(LoginActivity.this, "Artesano", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (rolId == 2) { // Rol de cliente
                            Toast.makeText(LoginActivity.this, "Cliente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (rolId == 3) { // Rol de delivery
                            Toast.makeText(LoginActivity.this, "Delivery", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        if (rolId == 4) { // Rol de administrador
                            Toast.makeText(LoginActivity.this, "Administrador", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, AdministradorActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    } else {
                        // Error en las credenciales
                        Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Error en la respuesta del servidor
                    Log.d("LoginActivity", "Código de respuesta: " + response.code());
                    Log.d("LoginActivity", "Cuerpo de respuesta: " + response.errorBody());
                    Toast.makeText(LoginActivity.this, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                // Error de conexión
                Log.e("LoginActivity", "Error en la conexión: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

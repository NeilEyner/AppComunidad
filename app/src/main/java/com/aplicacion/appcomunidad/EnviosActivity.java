package com.aplicacion.appcomunidad;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviosActivity extends AppCompatActivity {

    private RecyclerView rvEnvios;
    private EnvioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envios);
        rvEnvios = findViewById(R.id.rvEnvios);
        rvEnvios.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);
        if (userId == -1) {
            Toast.makeText(this, "No se encontró el ID del usuario", Toast.LENGTH_SHORT).show();
            return;
        }
        FloatingActionButton comprasButton = findViewById(R.id.Compras);
        comprasButton.setOnClickListener(v -> {
            Intent intent = new Intent(EnviosActivity.this, ComprasActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
        FloatingActionButton cerrarSesionButton = findViewById(R.id.CerrarSesion);
        cerrarSesionButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("userId");
            editor.apply();
            Intent intent = new Intent(EnviosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        fetchEnvios(userId);
    }

    private void fetchEnvios(int userId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        apiService.getEnvios(userId).enqueue(new Callback<List<Envio>>() {
            @Override
            public void onResponse(Call<List<Envio>> call, Response<List<Envio>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new EnvioAdapter(EnviosActivity.this, response.body());
                    rvEnvios.setAdapter(adapter);
                } else {
                    Toast.makeText(EnviosActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Envio>> call, Throwable t) {
                Toast.makeText(EnviosActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

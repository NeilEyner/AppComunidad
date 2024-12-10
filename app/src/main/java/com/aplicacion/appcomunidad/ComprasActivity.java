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

public class ComprasActivity extends AppCompatActivity {

    private RecyclerView rvCompras;
    private CompraAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        rvCompras = findViewById(R.id.rvCompras);
        rvCompras.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);

        FloatingActionButton cerrarSesionButton = findViewById(R.id.CerrarSesion);
        cerrarSesionButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("userId");
            editor.apply();
            Intent intent = new Intent(ComprasActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        fetchCompras(userId);
    }
    private void fetchCompras(int userId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        apiService.getCompras(userId).enqueue(new Callback<List<Compra>>() {
            @Override
            public void onResponse(Call<List<Compra>> call, Response<List<Compra>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new CompraAdapter(ComprasActivity.this, response.body());
                    rvCompras.setAdapter(adapter);
                } else {
                    Toast.makeText(ComprasActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Compra>> call, Throwable t) {
                Toast.makeText(ComprasActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

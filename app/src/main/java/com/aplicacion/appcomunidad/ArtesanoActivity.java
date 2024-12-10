package com.aplicacion.appcomunidad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;

public class ArtesanoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductoArtesanoAdapter productoAdapter;
    private List<ProductoArtesano> productos;
    private int idArtesano; // ID del artesano

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artesano);
        idArtesano = getIntent().getIntExtra("ROL_ID", -1);
        Log.d("ArtesanoActivity", "ID Artesano: " + idArtesano);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);

        FloatingActionButton comprasButton = findViewById(R.id.Compras);
        comprasButton.setOnClickListener(v -> {
            Intent intent = new Intent(ArtesanoActivity.this, ComprasActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        FloatingActionButton cerrarSesionButton = findViewById(R.id.CerrarSesion);
        cerrarSesionButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("userId");
            editor.apply();
            Intent intent = new Intent(ArtesanoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        obtenerProductosPorArtesano(idArtesano);
    }

    private void obtenerProductosPorArtesano(int idArtesano) {
        // Crear una instancia de la API
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        // Hacer la llamada para obtener los productos del artesano
        Call<List<ProductoArtesano>> call = apiService.listarProductosPorArtesano(idArtesano);

        call.enqueue(new Callback<List<ProductoArtesano>>() {
            @Override
            public void onResponse(Call<List<ProductoArtesano>> call, Response<List<ProductoArtesano>> response) {
                if (response.isSuccessful()) {
                    productos = response.body();
                    if (productos == null || productos.isEmpty()) {
                        Log.d("ArtesanoActivity", "No hay productos disponibles.");
                        productos = new ArrayList<>();
                    } else {
                        Log.d("ArtesanoActivity", "Productos obtenidos: " + productos.size());
                    }
                    productoAdapter = new ProductoArtesanoAdapter(ArtesanoActivity.this, productos);
                    recyclerView.setAdapter(productoAdapter);
                } else {
                    Toast.makeText(ArtesanoActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductoArtesano>> call, Throwable t) {
                // En caso de error en la conexión o en la solicitud
                Toast.makeText(ArtesanoActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ArtesanoActivity", "Error en la conexión", t);
            }
        });

    }
}

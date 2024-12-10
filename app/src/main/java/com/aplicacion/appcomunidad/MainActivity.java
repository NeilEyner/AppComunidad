package com.aplicacion.appcomunidad;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;
    private List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewProductos);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        obtenerContenidoCarousel();
        obtenerSobreNosotros();
        obtenerProductos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void obtenerProductos() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Producto>> call = apiService.obtenerProductos();

        call.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {

                    List<Producto> productos = response.body();
                    if (productos == null) {
                        productos = new ArrayList<>();
                    }
                    productoAdapter = new ProductoAdapter(MainActivity.this,productos);
                    recyclerView.setAdapter(productoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener productos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void obtenerSobreNosotros() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Map<String, String>> call = apiService.obtenerSobreNosotros();

        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, String> data = response.body();
                    String contenido = data.get("contenido");
                    TextView sobreNosotrosTextView = findViewById(R.id.tvSobreNosotros);
                    sobreNosotrosTextView.setText(contenido != null ? contenido : "Contenido no disponible");
                } else {
                    // Logs para depuración
                    Log.e("API_ERROR", "Código de error: " + response.code());
                    Log.e("API_ERROR", "Mensaje: " + response.message());
                    try {
                        Log.e("API_ERROR", "Cuerpo de error: " + response.errorBody().string());
                    } catch (Exception e) {
                        Log.e("API_ERROR", "No se pudo leer el cuerpo del error", e);
                    }
                    Toast.makeText(MainActivity.this, "Error al obtener información", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void obtenerContenidoCarousel() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Map<String, Object>> call = apiService.obtenerContenido();

        call.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CarouselItem> carouselItems = new ArrayList<>();
                    List<Map<String, Object>> rows = (List<Map<String, Object>>) response.body().get("rows");

                    for (Map<String, Object> row : rows) {
                        String tipoContenido = (String) row.get("Tipo_contenido");
                        if ("CARROUSEL".equals(tipoContenido)) {
                            carouselItems.add(new CarouselItem(
                                    (String) row.get("Titulo"),
                                    (String) row.get("Subtitulo"),
                                    (String) row.get("Contenido"),
                                    (String) row.get("Imagen")
                            ));
                        }
                    }

                    // Configura el ViewPager2
                    ViewPager2 carouselViewPager = findViewById(R.id.carouselViewPager);
                    carouselViewPager.setAdapter(new CarouselAdapter(MainActivity.this, carouselItems));
                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener contenido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
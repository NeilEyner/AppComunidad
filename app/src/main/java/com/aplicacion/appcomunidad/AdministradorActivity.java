package com.aplicacion.appcomunidad;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdministradorActivity extends AppCompatActivity {
    private List<Usuario> usuariosList;
    private UsuariosAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        progressBar = findViewById(R.id.progressBar);

        // Configurar el layout manager para el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar la lista de usuarios
        usuariosList = new ArrayList<>();

        // Crear y configurar el adaptador
        adapter = new UsuariosAdapter(usuariosList, this);
        recyclerView.setAdapter(adapter);

        // Cargar usuarios
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        // Mostrar progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Crear servicio API
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        // Llamar al método para obtener usuarios
        Call<List<Usuario>> call = apiService.obtenerUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                // Ocultar progress bar
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    // Limpiar lista existente
                    usuariosList.clear();

                    // Agregar nuevos usuarios
                    usuariosList.addAll(response.body());

                    // Notificar al adaptador
                    adapter.notifyDataSetChanged();
                } else {
                    // Mostrar mensaje de error
                    Toast.makeText(AdministradorActivity.this,
                            "Error al cargar usuarios: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                // Ocultar progress bar
                progressBar.setVisibility(View.GONE);

                // Mostrar mensaje de error de conexión
                Toast.makeText(AdministradorActivity.this,
                        "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
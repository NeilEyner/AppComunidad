package com.aplicacion.appcomunidad;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        recyclerView = findViewById(R.id.recyclerViewUsuarios);
        progressBar = findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usuariosList = new ArrayList<>();
        adapter = new UsuariosAdapter(usuariosList, this);
        recyclerView.setAdapter(adapter);
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        int userId = preferences.getInt("userId", -1);

        FloatingActionButton comprasButton = findViewById(R.id.Compras);
        comprasButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdministradorActivity.this, ComprasActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });
        FloatingActionButton cerrarSesionButton = findViewById(R.id.CerrarSesion);
        cerrarSesionButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("userId");
            editor.apply();
            Intent intent = new Intent(AdministradorActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        progressBar.setVisibility(View.VISIBLE);
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Usuario>> call = apiService.obtenerUsuarios();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    usuariosList.clear();
                    usuariosList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AdministradorActivity.this,
                            "Error al cargar usuarios: " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(AdministradorActivity.this,
                        "Error de conexión: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
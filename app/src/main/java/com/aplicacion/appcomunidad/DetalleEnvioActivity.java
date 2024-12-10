package com.aplicacion.appcomunidad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DetalleEnvioActivity extends AppCompatActivity {

    private RecyclerView rvDetallesProductos;
    private DetalleProductoAdapter<DetalleEnvio> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_envio);

        Envio envio = (Envio) getIntent().getSerializableExtra("envio");
        List<DetalleEnvio> detalles = envio.getDetalles();

        rvDetallesProductos = findViewById(R.id.rvDetallesProductos);
        rvDetallesProductos.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DetalleProductoAdapter<>(this, detalles);
        rvDetallesProductos.setAdapter(adapter);
    }
}

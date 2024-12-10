package com.aplicacion.appcomunidad;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnvioAdapter extends RecyclerView.Adapter<EnvioAdapter.EnvioViewHolder> {

    private Context context;
    private List<Envio> envios;

    public EnvioAdapter(Context context, List<Envio> envios) {
        this.context = context;
        this.envios = envios;
    }

    @NonNull
    @Override
    public EnvioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_envio, parent, false);
        return new EnvioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EnvioViewHolder holder, int position) {
        Envio envio = envios.get(position);

        holder.tvIDEnvio.setText("Envío: " + envio.getID());
        holder.tvEstado.setText("Estado: " + envio.getEstado());
        holder.tvCostoEnvio.setText("Costo: Bs./ " + envio.getCosto_envio());
        holder.tvDireccion.setText("Destino: " + envio.getDireccion_Destino());

        // Verificar el estado del envío y ocultar el botón si ya está entregado
        if ("ENTREGADO".equals(envio.getEstado())) {
            holder.btnConfirmarEntrega.setVisibility(View.GONE);  // Ocultar el botón si está entregado
        } else {
            holder.btnConfirmarEntrega.setVisibility(View.VISIBLE);  // Mostrar el botón si no está entregado
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleEnvioActivity.class);
            intent.putExtra("envio", envio); // Enviar objeto envío
            context.startActivity(intent);
        });

        holder.btnConfirmarEntrega.setOnClickListener(v -> {
            holder.tvEstado.setText("Estado: ENTREGADO");
            confirmarEntrega(envio.getID());
            holder.btnConfirmarEntrega.setVisibility(View.GONE); // Ocultar el botón después de confirmar entrega
        });
    }
    private void confirmarEntrega(String envioId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.confirmarEntrega(envioId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Envio confirmado");
                } else {
                    Log.e("API", "Error al confirmar envio: " + response.code());
                    Log.d("API", "ID del producto: " + envioId);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Error al conectar con el servidor", t);
            }
        });
    }



    @Override
    public int getItemCount() {
        return envios.size();
    }

    public static class EnvioViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDEnvio, tvEstado, tvCostoEnvio, tvDireccion;
        Button btnConfirmarEntrega;

        public EnvioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDEnvio = itemView.findViewById(R.id.tvIDEnvio);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvCostoEnvio = itemView.findViewById(R.id.tvCostoEnvio);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btnConfirmarEntrega = itemView.findViewById(R.id.btnConfirmarEntrega);
        }
    }
}

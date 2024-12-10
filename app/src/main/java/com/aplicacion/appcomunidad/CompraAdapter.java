package com.aplicacion.appcomunidad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraViewHolder> {

    private Context context;
    private List<Compra> compras;

    public CompraAdapter(Context context, List<Compra> compras) {
        this.context = context;
        this.compras = compras;
    }

    @NonNull
    @Override
    public CompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_compra, parent, false);
        return new CompraViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompraViewHolder holder, int position) {
        Compra compra = compras.get(position);

        holder.tvFechaCompra.setText(compra.getFecha());
        holder.tvEstadoCompra.setText(compra.getEstado());
        holder.tvTotalCompra.setText("Bs. " + compra.getTotal());
        holder.tvNombreArtesano.setText("Artesano: " + compra.getNombre());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetalleCompraActivity.class);
            intent.putExtra("compra", compra); // Enviar objeto compra
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return compras.size();
    }

    public static class CompraViewHolder extends RecyclerView.ViewHolder {
        TextView tvFechaCompra, tvEstadoCompra, tvTotalCompra, tvNombreArtesano;

        public CompraViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFechaCompra = itemView.findViewById(R.id.tvFechaCompra);
            tvEstadoCompra = itemView.findViewById(R.id.tvEstadoCompra);
            tvTotalCompra = itemView.findViewById(R.id.tvTotalCompra);
            tvNombreArtesano = itemView.findViewById(R.id.tvNombreArtesano);
        }
    }
}

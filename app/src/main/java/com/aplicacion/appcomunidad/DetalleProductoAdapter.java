package com.aplicacion.appcomunidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DetalleProductoAdapter<T> extends RecyclerView.Adapter<DetalleProductoAdapter.DetalleViewHolder> {

    private Context context;
    private List<T> detalles;

    // Constructor
    public DetalleProductoAdapter(Context context, List<T> detalles) {
        this.context = context;
        this.detalles = detalles;
    }

    @NonNull
    @Override
    public DetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detalle_producto, parent, false);
        return new DetalleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetalleViewHolder holder, int position) {
        T detalle = detalles.get(position);

        if (detalle instanceof DetalleProducto) {
            DetalleProducto detalleProducto = (DetalleProducto) detalle;
            holder.tvNombreProducto.setText(detalleProducto.getNombre());
            holder.tvEstadoProducto.setText("Estado: " + detalleProducto.getEstado());
            holder.tvCantidadProducto.setText("Cantidad: " + detalleProducto.getCantidad());
            Picasso.get().load(detalleProducto.getImagen_URL()).into(holder.ivImagenProducto);
        } else if (detalle instanceof DetalleEnvio) {
            DetalleEnvio detalleEnvio = (DetalleEnvio) detalle;
            holder.tvNombreProducto.setText(detalleEnvio.getNombre());
            holder.tvEstadoProducto.setText("Estado: " + detalleEnvio.getEstado());
            holder.tvCantidadProducto.setText("Cantidad: " + detalleEnvio.getCantidad());
            Picasso.get().load(detalleEnvio.getImagen_URL()).into(holder.ivImagenProducto);
        }
    }

    @Override
    public int getItemCount() {
        return detalles.size();
    }

    public static class DetalleViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagenProducto;
        TextView tvNombreProducto, tvEstadoProducto, tvCantidadProducto;

        public DetalleViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenProducto = itemView.findViewById(R.id.ivImagenProducto);
            tvNombreProducto = itemView.findViewById(R.id.tvNombreProducto);
            tvEstadoProducto = itemView.findViewById(R.id.tvEstadoProducto);
            tvCantidadProducto = itemView.findViewById(R.id.tvCantidadProducto);
        }
    }
}


package com.aplicacion.appcomunidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private Context context;
    private List<Producto> productos;

    public ProductoAdapter(Context context, List<Producto> productos) {
        this.context = context;
        this.productos = productos != null ? productos : new ArrayList<>(); // Evitar null
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout del item
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);

        // Asignar los valores de los productos a las vistas
        holder.nombreTextView.setText(producto.getNombre());
        holder.precioTextView.setText("Precio: " + producto.getPrecio());
        holder.stockTextView.setText("Stock: " + producto.getStock());

        // Cargar la imagen usando Glide
        Glide.with(context)
                .load(producto.getImagen_url())
                .error(R.drawable.ic_launcher_background) // Manejo de error en la carga de imágenes
                .into(holder.imagenImageView);
    }

    @Override
    public int getItemCount() {
        return productos != null ? productos.size() : 0;
    }

    // ViewHolder que representa cada item en el RecyclerView
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagenImageView;
        public TextView nombreTextView;
        public TextView precioTextView;
        public TextView stockTextView;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            imagenImageView = itemView.findViewById(R.id.imageViewProducto);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            precioTextView = itemView.findViewById(R.id.textViewPrecio);
            stockTextView = itemView.findViewById(R.id.textViewStock);
        }
    }
}

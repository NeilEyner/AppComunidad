package com.aplicacion.appcomunidad;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoArtesanoAdapter extends RecyclerView.Adapter<ProductoArtesanoAdapter.ProductoViewHolder> {

    private Context context;
    private List<ProductoArtesano> productos;

    // Constructor
    public ProductoArtesanoAdapter(Context context, List<ProductoArtesano> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public ProductoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout del item
        View view = LayoutInflater.from(context).inflate(R.layout.item_producto_artesano, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductoViewHolder holder, int position) {
        ProductoArtesano producto = productos.get(position);
        holder.nombreProducto.setText(producto.getNombre());
        holder.descripcionProducto.setText(producto.getDescripcion());
        holder.precioProducto.setText("Bs. " + producto.getPrecio());
        holder.stockProducto.setText("Stock: " + producto.getStock());

        // Mostrar disponibilidad (activo o no disponible)
        String disponibilidad = producto.getDisponibilidad().equals("1") ? "Disponible" : "No disponible";
        holder.disponibilidadProducto.setText("Disponibilidad: " + disponibilidad);

        // Cargar la imagen con Glide
        Glide.with(context)
                .load(producto.getImagen_url())
                .error(R.drawable.ic_launcher_background) // Manejo de error en la carga de imágenes
                .into(holder.imagenProducto);
        if (producto.getID() == null || producto.getID().isEmpty()) {
            Log.e("API", "El ID del producto es nulo o vacío");
            return;
        }

        holder.botonAumentarStock.setOnClickListener(v -> {
            String stockString = producto.getStock();
            String idProducto = producto.getID();

            int stock = Integer.parseInt(stockString);
            int nuevoStock = stock + 1;
            producto.setStock(String.valueOf(nuevoStock));
            holder.stockProducto.setText("Stock: " + nuevoStock);
            aumentarStock(producto.getID());
        });

        holder.botonReducirStock.setOnClickListener(v -> {
            String stockString = producto.getStock();
            int stock = Integer.parseInt(stockString);
            if (stock > 0) {
                int nuevoStock = stock - 1;
                producto.setStock(String.valueOf(nuevoStock));
                holder.stockProducto.setText("Stock: " + nuevoStock);
                reducirStock(producto.getID());
            }
        });

        holder.botonDisponibilidad.setOnClickListener(v -> {
            String disponibilidadString = producto.getDisponibilidad();
            if ("Disponible".equals(disponibilidadString)) {
                producto.setDisponibilidad("No disponible");
                holder.disponibilidadProducto.setText("Disponibilidad: No disponible");
            } else {
                producto.setDisponibilidad("Disponible");
                holder.disponibilidadProducto.setText("Disponibilidad: Disponible");
            }
            disponibleProducto(producto.getID());
        });

    }
    private void aumentarStock(String productoId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.aumentarStock(productoId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Stock del producto aumentado correctamente");
                } else {
                    Log.e("API", "Error al aumentar el stock: " + response.code());
                    Log.d("API", "ID del producto: " + productoId);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Error al conectar con el servidor", t);
            }
        });
    }
    private void reducirStock(String productoId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.reducirStock(productoId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Stock del producto reducido correctamente");
                } else {
                    Log.e("API", "Error al reducir el stock: " + response.code());
                    Log.d("API", "ID del producto: " + productoId);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Error al conectar con el servidor", t);
            }
        });
    }
    private void disponibleProducto(String productoId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.disponibleProducto(productoId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Estado de disponibilidad del producto actualizado correctamente");
                } else {
                    Log.e("API", "Error al actualizar la disponibilidad: " + response.code());
                    Log.d("API", "ID del producto: " + productoId);
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
        return productos.size();
    }
    public static class ProductoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreProducto;
        TextView descripcionProducto;
        TextView precioProducto;
        ImageView imagenProducto;
        TextView stockProducto;  // Vista para el stock
        TextView disponibilidadProducto;

        Button botonAumentarStock;
        Button botonReducirStock;
        Button botonDisponibilidad;

        public ProductoViewHolder(View itemView) {
            super(itemView);

            // Inicializar las vistas
            nombreProducto = itemView.findViewById(R.id.nombreProducto);
            descripcionProducto = itemView.findViewById(R.id.descripcionProducto);
            precioProducto = itemView.findViewById(R.id.precioProducto);
            stockProducto = itemView.findViewById(R.id.stockProducto);  // Inicializamos el TextView del stock
            disponibilidadProducto = itemView.findViewById(R.id.disponibilidadProducto);  // Inicializamos el TextView de disponibilidad
            imagenProducto = itemView.findViewById(R.id.imagenProducto);

            // Inicializar los botones
            botonAumentarStock = itemView.findViewById(R.id.btnAumentarStock);
            botonReducirStock = itemView.findViewById(R.id.btnReducirStock);
            botonDisponibilidad = itemView.findViewById(R.id.btnDisponibilidad);
        }
    }





}

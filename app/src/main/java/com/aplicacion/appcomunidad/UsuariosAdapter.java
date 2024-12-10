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

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder> {
    private List<Usuario> usuariosList;
    private Context context;

    // Constructor
    public UsuariosAdapter(List<Usuario> usuariosList, Context context) {
        this.usuariosList = usuariosList;
        this.context = context;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuariosList.get(position);
        holder.nombreTextView.setText(usuario.getNombre());
        holder.correoTextView.setText(usuario.getCorreo_electronico());
        holder.estadoTextView.setText(usuario.getEstado());
        holder.estadoButton.setText(usuario.getEstado().equals("ACTIVO") ? "Inhabilitar" : "Habilitar");


        if (usuario.getimagen_url() != null && !usuario.getimagen_url().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(usuario.getimagen_url())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .placeholder(R.drawable.default_profile)
                    .error(R.drawable.default_profile)
                    .circleCrop()
                    .into(holder.imagenPerfil);
        } else {
            holder.imagenPerfil.setImageResource(R.drawable.default_profile);
        }
        holder.estadoButton.setOnClickListener(v -> {
            String nuevoEstado = usuario.getEstado().equals("ACTIVO") ? "INACTIVO" : "ACTIVO";
            usuario.setEstado(nuevoEstado);
            holder.estadoButton.setText(nuevoEstado.equals("ACTIVO") ? "Inhabilitar" : "Habilitar");
            notifyItemChanged(position);
            cambiarEstadoUsuario(usuario.getID());
        });


//        // Configurar click listener
//        holder.itemView.setOnClickListener(v -> {
//            // Abrir detalles del usuario
//            Intent intent = new Intent(context, DetalleUsuarioActivity.class);
//            intent.putExtra("USUARIO_ID", usuario.getID());
//            context.startActivity(intent);
//        });
    }
    private void cambiarEstadoUsuario(String usuarioId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Log.d("API", "URL completa: " + "https://example.com/api/cambiarEstadoUsuario/" + usuarioId);

        Call<Void> call = apiService.cambiarEstadoUsuario(usuarioId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("API", "Estado del usuario actualizado correctamente");
                } else {
                    Log.e("API", "Error al actualizar el estado: " + response.code());
                    Log.d("API", "ID del usuario: " + usuarioId);

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
        return usuariosList.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView correoTextView;
        TextView estadoTextView;
        ImageView imagenPerfil;
        Button estadoButton;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            correoTextView = itemView.findViewById(R.id.textViewCorreo);
            estadoTextView = itemView.findViewById(R.id.textViewEstado);
            imagenPerfil = itemView.findViewById(R.id.imageViewPerfil);
            estadoButton = itemView.findViewById(R.id.estadoViewButton);
        }
    }


}
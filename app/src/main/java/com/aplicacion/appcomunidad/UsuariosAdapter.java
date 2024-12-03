package com.aplicacion.appcomunidad;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.UsuarioViewHolder> {
    private List<Usuario> usuariosList;
    private Context context;

    // Constructor
    public UsuariosAdapter(List<Usuario> usuariosList, Context context) {
        this.usuariosList = usuariosList;
        this.context = context;
    }

    // Crear nueva vista
    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    // Vincular datos a la vista
    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuariosList.get(position);

        // Establecer datos
        holder.nombreTextView.setText(usuario.getNombre());
        holder.correoTextView.setText(usuario.getCorreo_electronico());
        holder.estadoTextView.setText(usuario.getEstado());

        // Cargar imagen de perfil si está disponible
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

//        // Configurar click listener
//        holder.itemView.setOnClickListener(v -> {
//            // Abrir detalles del usuario
//            Intent intent = new Intent(context, DetalleUsuarioActivity.class);
//            intent.putExtra("USUARIO_ID", usuario.getID());
//            context.startActivity(intent);
//        });
    }

    // Obtener número de elementos
    @Override
    public int getItemCount() {
        return usuariosList.size();
    }

    // Clase ViewHolder
    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView correoTextView;
        TextView estadoTextView;
        ImageView imagenPerfil;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.textViewNombre);
            correoTextView = itemView.findViewById(R.id.textViewCorreo);
            estadoTextView = itemView.findViewById(R.id.textViewEstado);
            imagenPerfil = itemView.findViewById(R.id.imageViewPerfil);
        }
    }
}
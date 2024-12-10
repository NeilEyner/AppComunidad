package com.aplicacion.appcomunidad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder> {
    private Context context;
    private List<CarouselItem> items;

    public CarouselAdapter(Context context, List<CarouselItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carousel, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        CarouselItem item = items.get(position);

        holder.title.setText(item.getTitulo());
        holder.subtitle.setText(item.getSubtitulo());
        holder.content.setText(item.getContenido());
        Glide.with(context)
                .load(item.getImagen())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, content;
        ImageView image;

        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.carouselTitle);
            subtitle = itemView.findViewById(R.id.carouselSubtitle);
            content = itemView.findViewById(R.id.carouselContent);
            image = itemView.findViewById(R.id.carouselImage);
        }
    }
}

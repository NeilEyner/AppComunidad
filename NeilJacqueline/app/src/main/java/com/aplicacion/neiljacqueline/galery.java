package com.aplicacion.neiljacqueline;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class galery extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.galery);

        recyclerView = findViewById(R.id.imageGalleryRecyclerView);
        int spanCount = 2; // Número de columnas
        int spacing = 20; // Espaciado entre elementos en píxeles
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        ArrayList<Integer> imageList = getImageList();
        galleryAdapter = new GalleryAdapter(this, imageList);
        recyclerView.setAdapter(galleryAdapter);
    }

    private ArrayList<Integer> getImageList() {
        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);
        imageList.add(R.drawable.image4);
        imageList.add(R.drawable.image5);
        imageList.add(R.drawable.image6);
        return imageList;
    }

    // GalleryAdapter class
    private static class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
        private Context context;
        private ArrayList<Integer> images;

        public GalleryAdapter(Context context, ArrayList<Integer> images) {
            this.context = context;
            this.images = images;
        }

        @NonNull
        @Override
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_gallery_image, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        static class GalleryViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            GalleryViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.galleryImageView);
            }
        }
    }

    // GridSpacingItemDecoration class
    private static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;
                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}
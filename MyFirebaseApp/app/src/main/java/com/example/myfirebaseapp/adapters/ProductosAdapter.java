package com.example.myfirebaseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirebaseapp.R;
import com.example.myfirebaseapp.models.Productos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder> {

    private List<Productos> Lproductos;
    private OnProductosClickListener onProductosClickListener;
    private OnFavoriteClickListener onFavoriteClickListener;

    public interface OnProductosClickListener {
        void onProductosClick(Productos productos);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Productos productos, boolean isFavorite);
    }

    public ProductosAdapter(List<Productos> Lproductos, OnProductosClickListener onProductosClickListener,
                         OnFavoriteClickListener onFavoriteClickListener) {
        this.Lproductos = Lproductos;
        this.onProductosClickListener = onProductosClickListener;
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productos, parent, false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        Productos productos = Lproductos.get(position);
        holder.bind(productos);
    }

    @Override
    public int getItemCount() {
        return Lproductos.size();
    }

    public void updateProductos(List<Productos> newProducts) {
        for (Productos newProductos : newProducts) {
            for (int i = 0; i < Lproductos.size(); i++) {
                Productos oldProductos = Lproductos.get(i);
                if (oldProductos.getId().equals(newProductos.getId())) {
                    oldProductos.setFavorite(newProductos.isFavorite());
                    notifyItemChanged(i);
                    break;
                }
            }
        }
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        ImageView productosImageView, favoriteImageView;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.productosTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.productosDescriptionTextView);
            productosImageView = itemView.findViewById(R.id.productosImageView);
            favoriteImageView = itemView.findViewById(R.id.favoriteImageView);
        }

        public void bind(Productos productos) {
            titleTextView.setText(productos.getTitulo());
            descriptionTextView.setText(productos.getDescripcion());

            // Cargar imagen con Picasso
            Picasso.get().load(productos.getImagen()).into(productosImageView);

            // Asignar contentDescription dinámico para la imagen
            productosImageView.setContentDescription("Imagen de la receta: " + productos.getTitulo());

            // Cambiar el ícono de favorito y su descripción
            if (productos.isFavorite()) {
                favoriteImageView.setImageResource(R.drawable.ic_favorite);
                favoriteImageView.setContentDescription("Quitar " + productos.getTitulo() + " de favoritos");
            } else {
                favoriteImageView.setImageResource(R.drawable.ic_favorite_border);
                favoriteImageView.setContentDescription("Agregar " + productos.getTitulo() + " a favoritos");
            }

            favoriteImageView.setOnClickListener(v -> {
                if (onFavoriteClickListener != null) {
                    onFavoriteClickListener.onFavoriteClick(productos, !productos.isFavorite());
                }
            });

            itemView.setOnClickListener(v -> {
                if (onProductosClickListener != null) {
                    onProductosClickListener.onProductosClick(productos);
                }
            });
        }
    }
}

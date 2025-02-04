package com.example.myfirebaseapp.adapters;

import android.content.Context;
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

    private Context context;
    private List<Productos> Lproductos;
    private OnItemClickListener onItemClickListener;

    public ProductosAdapter(Context context, List<Productos> Lproductos) {
        this.context = context;
        this.Lproductos = Lproductos;
    }

    public interface OnItemClickListener {
        void onItemClick(Productos productos);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // Método para actualizar la lista de productos
    public void setProductos(List<Productos> Lproductos) {
        this.Lproductos = Lproductos;
        notifyDataSetChanged();  // Notificar que los datos han cambiado
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_productos, parent, false);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        Productos productos = Lproductos.get(position);

        holder.tvTitle.setText(productos.getTitulo());
        holder.tvDescription.setText(productos.getDescripcion());
        Picasso.get().load(productos.getImagen()).into(holder.ivImage);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(productos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Lproductos != null ? Lproductos.size() : 0;
    }

    public static class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription;
        ImageView ivImage;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}

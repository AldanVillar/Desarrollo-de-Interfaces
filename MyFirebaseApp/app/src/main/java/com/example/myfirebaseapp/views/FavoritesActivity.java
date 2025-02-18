package com.example.myfirebaseapp.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirebaseapp.R;
import com.example.myfirebaseapp.adapters.ProductosAdapter;
import com.example.myfirebaseapp.viewmodels.FavoritesViewModel;

public class FavoritesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductosAdapter adapter;
    private FavoritesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initializeViews();
        setupViewModel();
        observeFavorites();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
    }

    private void observeFavorites() {
        viewModel.getFavoriteProductos().observe(this, products -> {
            if (products != null && !products.isEmpty()) {
                if (adapter == null) {
                    adapter = new ProductosAdapter(
                            products,
                            productos -> {
                                // Implementar navegación al detalle si se desea
                            },
                            viewModel::toggleFavorite
                    );
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateProductos(products);
                }
            } else {
                Toast.makeText(this, "No tienes recetas favoritas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
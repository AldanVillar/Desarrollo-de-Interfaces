package com.example.myfirebaseapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myfirebaseapp.R;
import com.example.myfirebaseapp.adapters.ProductosAdapter;
import com.example.myfirebaseapp.models.Productos;
import com.example.myfirebaseapp.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductosAdapter productosAdapter;
    private FirebaseAuth mAuth;
    private Button btnLogout;
    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        btnLogout = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        Button btnFavorites = findViewById(R.id.btn_favorites);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Iniciar adaptador vacío
        productosAdapter = new ProductosAdapter(this, null);
        recyclerView.setAdapter(productosAdapter);

        // Usamos ViewModelProvider para obtener el ViewModel
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Observar el ViewModel para obtener los cambios en la lista de productos
        dashboardViewModel.getProductos().observe(this, new Observer<List<Productos>>() {
            @Override
            public void onChanged(List<Productos> productos) {
                productosAdapter.setProductos(productos);
            }
        });

        // Cargar los productos desde Firebase
        dashboardViewModel.loadProductos();

        // Configurar el botón de Logout
        btnLogout.setOnClickListener(v -> logoutUser());

        // Configurar el click en cada item del RecyclerView
        productosAdapter.setOnItemClickListener(new ProductosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Productos productos) {
                // Abrir DetailActivity
                Intent intent = new Intent(DashboardActivity.this, DetailActivity.class);

                // Pasar los datos
                intent.putExtra("title", productos.getTitulo());
                intent.putExtra("description", productos.getDescripcion());
                intent.putExtra("imageUrl", productos.getImagen());

                // Iniciar DetailActivity
                startActivity(intent);
            }
        });
        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, FavoriteActivity.class);
            startActivity(intent);
        });
    }

    private void logoutUser() {
        mAuth.signOut();
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
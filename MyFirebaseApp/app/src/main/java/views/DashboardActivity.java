package views;

import static android.os.Build.VERSION_CODES.R;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import models.Productos;
import adapters.ProductosAdapter;
import viewmodels.DashboardViewModel;

public class DashboardActivity extends AppCompatActivity {

    private DashboardViewModel dashboardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductosAdapter adapter = new ProductosAdapter();
        recyclerView.setAdapter(adapter);

        dashboardViewModel.getRecipesLiveData().observe(this, productos -> {
            if (productos != null) {
                adapter.setProductos(productos);
            }
        });

        dashboardViewModel.fetchRecipes();

        adapter.setOnItemClickListener(productos -> {
            Intent intent = new Intent(DashboardActivity.this, DetailActivity.class);
            intent.putExtra("title", productos.getTitle());
            intent.putExtra("description", productos.getDescription());
            intent.putExtra("imageUrl", productos.getImageUrl());
            startActivity(intent);
        });
    }
}
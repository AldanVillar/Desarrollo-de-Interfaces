package com.example.myfirebaseapp.views;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myfirebaseapp.R;
import com.example.myfirebaseapp.adapters.ProductosAdapter;
import com.example.myfirebaseapp.models.Productos;
import com.example.myfirebaseapp.viewmodels.DashboardViewModel;
public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductosAdapter adapter;
    private DashboardViewModel dashboardViewModel;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        observeProductos();
        return view;
    }
    private void observeProductos() {
        dashboardViewModel.getLproductos().observe(getViewLifecycleOwner(), Lproductos -> {
            if (Lproductos != null && !Lproductos.isEmpty()) {
                if (adapter == null) {
                    adapter = new ProductosAdapter(
                            Lproductos,
                            this::onProductosClick,
                            (productos, isFavorite) -> dashboardViewModel.toggleFavorite(productos, isFavorite)
                    );
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateProductos(Lproductos);
                }
            } else {
                Toast.makeText(getContext(), "No se encontraron recetas", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void onProductosClick(Productos productos) {
        Bundle bundle = new Bundle();
        bundle.putString("title", productos.getTitulo());
        bundle.putString("description", productos.getDescripcion());
        bundle.putString("imageUrl", productos.getImagen());
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }
}
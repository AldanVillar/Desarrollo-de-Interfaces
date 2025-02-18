package com.example.myfirebaseapp.views;
import android.annotation.SuppressLint;
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
import com.example.myfirebaseapp.viewmodels.FavoritesViewModel;

public class FavoritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductosAdapter adapter;
    private FavoritesViewModel viewModel;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.favoritesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        observeFavorites();
        return view;
    }
    private void observeFavorites() {
        viewModel.getFavoriteProductos().observe(getViewLifecycleOwner(), Lproductos -> {
            if (Lproductos != null && !Lproductos.isEmpty()) {
                if (adapter == null) {
                    adapter = new ProductosAdapter(
                            Lproductos,
                            productos -> {
                                // Navigate to detail fragment
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
                            },
                            viewModel::toggleFavorite
                    );
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.updateProductos(Lproductos);
                }
            } else {
                Toast.makeText(getContext(), "No tienes recetas favoritas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.myfirebaseapp.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirebaseapp.models.Productos;
import com.example.myfirebaseapp.repositories.DashboardRepository;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {
    private final DashboardRepository repository;
    private final MutableLiveData<List<Productos>> favoriteProductos;

    public FavoritesViewModel(Application application) {
        super(application);
        repository = new DashboardRepository();
        favoriteProductos = new MutableLiveData<>();
        loadFavorites();
    }

    private void loadFavorites() {
        repository.getFavoriteProductos().observeForever(productos ->
                favoriteProductos.setValue(productos)
        );
    }

    public void toggleFavorite(Productos productos, boolean isFavorite) {
        repository.toggleFavorite(productos.getId(), isFavorite);
    }

    public LiveData<List<Productos>> getFavoriteProductos() {
        return favoriteProductos;
    }
}
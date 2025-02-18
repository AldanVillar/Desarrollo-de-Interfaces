package com.example.myfirebaseapp.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirebaseapp.models.Productos;
import com.example.myfirebaseapp.repositories.DashboardRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {
    private final DashboardRepository dashboardRepository;
    private final MutableLiveData<List<Productos>> Lproductos;

    public DashboardViewModel(Application application) {
        super(application);
        dashboardRepository = new DashboardRepository();
        Lproductos = new MutableLiveData<>();
        loadProductos();
    }

    private void loadProductos() {
        dashboardRepository.getProductosFromDatabase().observeForever(productos ->
                Lproductos.setValue(productos)
        );
    }

    public void toggleFavorite(Productos productos, boolean isFavorite) {
        dashboardRepository.toggleFavorite(productos.getId(), isFavorite);
    }

    public LiveData<List<Productos>> getLproductos() {
        return Lproductos;
    }
}
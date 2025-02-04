package com.example.myfirebaseapp.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.myfirebaseapp.models.Productos;
import com.example.myfirebaseapp.repositories.DashboardRepository;

import java.util.List;

public class DashboardViewModel extends AndroidViewModel {

    private DashboardRepository repository;
    private MutableLiveData<List<Productos>> productos = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public DashboardViewModel(Application application) {
        super(application);
        repository = new DashboardRepository();
    }

    public LiveData<List<Productos>> getProductos() {
        return productos;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loadProductos() {
        repository.loadProductos(new DashboardRepository.ProductosCallback() {
            @Override
            public void onSuccess(List<Productos> productosList) {
                productos.setValue(productosList);
            }

            @Override
            public void onFailure(String errorMessage) {
                DashboardViewModel.this.errorMessage.setValue(errorMessage);
            }
        });
    }
}
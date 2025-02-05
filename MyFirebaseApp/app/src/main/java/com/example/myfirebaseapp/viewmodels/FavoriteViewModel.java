package com.example.myfirebaseapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class FavoriteViewModel extends ViewModel {

    private MutableLiveData<List<String>> favorites = new MutableLiveData<>();

    public LiveData<List<String>> getFavorites() {
        return favorites;
    }

    public void toggleFavorite(String itemId) {
        // Implementar lógica de agregar/eliminar desde Firebase
    }

    public void loadFavorites(String userId) {
        // Cargar favoritos desde Firebase
    }
}

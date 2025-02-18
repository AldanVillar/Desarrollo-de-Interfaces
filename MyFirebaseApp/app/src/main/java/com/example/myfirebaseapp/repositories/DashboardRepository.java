package com.example.myfirebaseapp.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myfirebaseapp.models.Productos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {
    private final DatabaseReference databaseReference;
    private final DatabaseReference userFavoritesReference;
    private final FirebaseAuth mAuth;

    public DashboardRepository() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("productos");
        userFavoritesReference = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public LiveData<List<Productos>> getFavoriteProductos() {
        MutableLiveData<List<Productos>> favouriteProductosLiveData = new MutableLiveData<>();
        String userId = mAuth.getCurrentUser().getUid();

        userFavoritesReference.child(userId).child("favoritos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot favoritesSnapshot) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot productsSnapshot) {
                        List<Productos> favoriteProductos = new ArrayList<>();

                        for (DataSnapshot productosSnapshot : productsSnapshot.getChildren()) {
                            if (favoritesSnapshot.hasChild(productosSnapshot.getKey())) {
                                Productos productos = productosSnapshot.getValue(Productos.class);
                                if (productos != null) {
                                    productos.setId(productosSnapshot.getKey());
                                    productos.setFavorite(true);
                                    favoriteProductos.add(productos);
                                }
                            }
                        }

                        favouriteProductosLiveData.setValue(favoriteProductos);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejar error
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar error
            }
        });

        return favouriteProductosLiveData;
    }

    public void toggleFavorite(String productosId, boolean isFavorite) {
        String userId = mAuth.getCurrentUser().getUid();
        userFavoritesReference
                .child(userId)
                .child("favoritos")
                .child(productosId)
                .setValue(isFavorite ? true : null);  // Si es false, eliminamos la entrada
    }

    public LiveData<List<Productos>> getProductosFromDatabase() {
        MutableLiveData<List<Productos>> productosListLiveData = new MutableLiveData<>();
        String userId = mAuth.getCurrentUser().getUid();

        // Primero obtener los favoritos del usuario
        userFavoritesReference.child(userId).child("favoritos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot favoritesSnapshot) {
                // Luego obtener de los productos
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot recipesSnapshot) {
                        List<Productos> productosList = new ArrayList<>();

                        for (DataSnapshot snapshot : recipesSnapshot.getChildren()) {
                            Productos productos = snapshot.getValue(Productos.class);
                            if (productos != null) {
                                productos.setId(snapshot.getKey());
                                // Verificar si el producto está en favoritos
                                productos.setFavorite(favoritesSnapshot.hasChild(productos.getId()));
                                productosList.add(productos);
                            }
                        }

                        productosListLiveData.setValue(productosList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejar error
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar error
            }
        });

        return productosListLiveData;
    }
}

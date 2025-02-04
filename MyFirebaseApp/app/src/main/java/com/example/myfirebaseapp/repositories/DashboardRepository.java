package com.example.myfirebaseapp.repositories;

import com.example.myfirebaseapp.models.Productos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {

    private DatabaseReference mDatabase;

    public DashboardRepository() {
        mDatabase = FirebaseDatabase.getInstance().getReference("productos");
    }

    // Método para cargar los productos desde Firebase
    public void loadProductos(final ProductosCallback callback) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Productos> productosList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Productos productos = snapshot.getValue(Productos.class);
                    if (productos != null) {
                        productos.setId(snapshot.getKey());  // Asignar el ID del producto
                        productosList.add(productos);
                    }
                }
                callback.onSuccess(productosList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onFailure(databaseError.getMessage());
            }
        });
    }

    public interface ProductosCallback {
        void onSuccess(List<Productos> productosList);
        void onFailure(String errorMessage);
    }
}

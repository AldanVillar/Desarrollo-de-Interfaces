package com.example.myfirebaseapp.repositories;

import com.example.myfirebaseapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
    }

    // Método para registrar un usuario
    public void registerUser(String email, String password, String fullName, String phone, String address, final OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Crear un User con los datos
                            User newUser = new User(fullName, email, phone, address);

                            // Guardar los datos en la base de datos
                            mDatabase.child(user.getUid()).setValue(newUser)
                                    .addOnCompleteListener(dbTask -> {
                                        listener.onComplete(task);
                                    });
                        }
                    } else {
                        listener.onComplete(task); // Llamar al listener en caso de error
                    }
                });
    }

    // Método para iniciar sesión
    public void loginUser(String email, String password, final OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);  // Devolver el resultado de la autenticación
    }

    // Método para cerrar sesión
    public void logoutUser() {
        mAuth.signOut();
    }

    // Método para obtener el usuario actual
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();  // Para obtener el ID del usuario actual.
    DatabaseReference userFavoritesRef = database.child("usuarios").child(userId).child("favoritos");

    public void toggleFavorite(String itemId) {
        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> favorites = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    favorites.add(snapshot.getValue(String.class));
                }

                if (favorites.contains(itemId)) {
                    favorites.remove(itemId);  // Eliminar de favoritos
                } else {
                    favorites.add(itemId);  // Agregar a favoritos
                }

                userFavoritesRef.setValue(favorites);  // Actualizar la lista de favoritos
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar el error
            }
        });
    }
}
package repositories;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import models.User;

public class UserRepository {

    private FirebaseAuth mAuth;
    private DatabaseReference database;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
    }

    // Registrar nuevo usuario
    public void registerUser(String email, String password, User user, OnRegisterCompleteListener listener) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String userId = firebaseUser.getUid();
                        database.child("users").child(userId).setValue(user)
                                .addOnSuccessListener(aVoid -> listener.onSuccess())
                                .addOnFailureListener(listener::onFailure);
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    // Interface para manejar resultados de registro
    public interface OnRegisterCompleteListener {
        void onSuccess();
        void onFailure(Exception e);
    }
    public void loginUser(String email, String password, OnLoginCompleteListener listener) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onFailure(task.getException());
                    }
                });
    }

    public interface OnLoginCompleteListener {
        void onSuccess();
        void onFailure(Exception e);
    }
}
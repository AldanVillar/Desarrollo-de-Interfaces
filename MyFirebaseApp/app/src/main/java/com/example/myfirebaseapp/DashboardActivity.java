package com.example.myfirebaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity {
    private TextView tituloView, descripcionView;
    private ImageView imagenView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tituloView = findViewById(R.id.textViewTitle);
        descripcionView = findViewById(R.id.textViewDescription);
        imagenView = findViewById(R.id.imageView);
        logoutButton = findViewById(R.id.buttonLogout);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("productos");
        database.child("producto1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String titulo = snapshot.child("titulo").getValue(String.class);
                String descripcion = snapshot.child("descripcion").getValue(String.class);
                String imagenUrl = snapshot.child("imagen").getValue(String.class);

                tituloView.setText(titulo);
                descripcionView.setText(descripcion);
                Picasso.get().load(imagenUrl).into(imagenView);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Manejar error
            }
        });

        // Cerrar sesión
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
            finish();
        });
    }
}
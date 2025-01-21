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
    private TextView titleView, descriptionView;
    private ImageView imageView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        titleView = findViewById(R.id.textViewTitle);
        descriptionView = findViewById(R.id.textViewDescription);
        imageView = findViewById(R.id.imageView);
        logoutButton = findViewById(R.id.buttonLogout);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("items");
        database.child("item1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String title = snapshot.child("title").getValue(String.class);
                String description = snapshot.child("description").getValue(String.class);
                String imageUrl = snapshot.child("image").getValue(String.class);

                titleView.setText(title);
                descriptionView.setText(description);
                Picasso.get().load(imageUrl).into(imageView);
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
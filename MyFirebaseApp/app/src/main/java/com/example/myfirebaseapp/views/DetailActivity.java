package com.example.myfirebaseapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myfirebaseapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvDescription;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Referencias a los elementos de la interfaz
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        ivImage = findViewById(R.id.ivImage);

        // Recibir los datos
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String imageUrl = intent.getStringExtra("imageUrl");

            // Establecer los valores en la interfaz
            tvTitle.setText(title);
            tvDescription.setText(description);

            // Usar Picasso para cargar la imagen desde la URL
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(ivImage);
            }
        }
        FloatingActionButton fabFavorite = findViewById(R.id.fab_favorite);
        String itemId = "item_id"; // Este debe ser el ID único del producto/item

        // Cargar estado de favorito
        DatabaseReference userFavoritesRef = FirebaseDatabase.getInstance().getReference("usuarios")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favoritos");

        userFavoritesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isFavorite = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getValue(String.class).equals(itemId)) {
                        isFavorite = true;
                        break;
                    }
                }
                fabFavorite.setImageResource(isFavorite ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        // Alternar estado de favorito al hacer clic
        fabFavorite.setOnClickListener(view -> {
            toggleFavorite(itemId);  // Usar el método que escribimos anteriormente
        });
    }
}
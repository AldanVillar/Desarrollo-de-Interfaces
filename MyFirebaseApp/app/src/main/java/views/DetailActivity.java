package views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.app.R;

public class DetailActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Glide.with(this).load(imageUrl).into(imageView);
    }
}
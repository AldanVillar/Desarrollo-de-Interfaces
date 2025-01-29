package repositories;

import models.Productos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {

    private DatabaseReference database;

    public DashboardRepository() {
        database = FirebaseDatabase.getInstance().getReference().child("productos");
    }

    public void fetchRecipes(OnRecipesFetchedListener listener) {
        database.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                List<Productos> productos = new ArrayList<>();
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    Productos recipe = recipeSnapshot.getValue(Productos.class);
                    productos.add(recipe);
                }
                listener.onSuccess(productos);
            } else {
                listener.onFailure(task.getException());
            }
        });
    }

    public interface OnRecipesFetchedListener {
        void onSuccess(List<Productos> recipes);
        void onFailure(Exception e);
    }
}

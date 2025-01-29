package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import models.Productos;
import repositories.DashboardRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private DashboardRepository dashboardRepository;
    private MutableLiveData<List<Productos>> recipesLiveData;

    public DashboardViewModel() {
        dashboardRepository = new DashboardRepository();
        recipesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Productos>> getRecipesLiveData() {
        return recipesLiveData;
    }

    public void fetchRecipes() {
        dashboardRepository.fetchRecipes(new DashboardRepository.OnRecipesFetchedListener() {
            @Override
            public void onSuccess(List<Productos> recipes) {
                recipesLiveData.postValue(recipes);
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar el error
            }
        });
    }
}


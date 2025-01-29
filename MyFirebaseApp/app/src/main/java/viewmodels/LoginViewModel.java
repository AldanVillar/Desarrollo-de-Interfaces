package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import repositories.UserRepository;

public class LoginViewModel extends ViewModel {

    private UserRepository userRepository;
    private MutableLiveData<String> errorMessage;

    public LoginViewModel() {
        userRepository = new UserRepository();
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void loginUser(String email, String password) {
        userRepository.loginUser(email, password, new UserRepository.OnLoginCompleteListener() {
            @Override
            public void onSuccess() {
                errorMessage.postValue("Inicio de sesión exitoso");
            }

            @Override
            public void onFailure(Exception e) {
                errorMessage.postValue(e.getMessage());
            }
        });
    }
}

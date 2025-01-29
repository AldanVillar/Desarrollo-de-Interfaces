package viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import models.User;
import repositories.UserRepository;

public class RegisterViewModel extends ViewModel {

    private UserRepository userRepository;
    private MutableLiveData<String> errorMessage;

    public RegisterViewModel() {
        userRepository = new UserRepository();
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void registerUser(String name, String email, String password, String confirmPassword, String phone, String address) {
        if (password.equals(confirmPassword)) {
            User user = new User(name, email, phone, address);
            userRepository.registerUser(email, password, user, new UserRepository.OnRegisterCompleteListener() {
                @Override
                public void onSuccess() {
                    // Éxito en el registro
                    errorMessage.postValue("Registro exitoso");
                }

                @Override
                public void onFailure(Exception e) {
                    // Error en el registro
                    errorMessage.postValue(e.getMessage());
                }
            });
        } else {
            errorMessage.postValue("Las contraseñas no coinciden");
        }
    }
}
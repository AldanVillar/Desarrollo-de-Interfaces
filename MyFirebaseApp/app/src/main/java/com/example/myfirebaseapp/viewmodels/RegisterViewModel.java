package com.example.myfirebaseapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;
import com.example.myfirebaseapp.models.User;
import com.example.myfirebaseapp.repositories.UserRepository;

public class RegisterViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();
    private static final String TAG = "RegisterViewModel";
    private final MutableLiveData<String> validationError = new MutableLiveData<>();

    public RegisterViewModel() {
        userRepository = new UserRepository();
    }
    public LiveData<String> getValidationError() {
        return validationError;
    }
    public LiveData<String> getErrorMessage() {
        return userRepository.getErrorMessage();
    }
    public LiveData<Boolean> getRegistrationResult() {
        return registrationResult;
    }

    public void registerUser(String fullName, String email, String password, String confirmPassword, String phone, String address) {
        Log.d(TAG, "Iniciando validación de registro");
        // Validaciones
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
            validationError.setValue("Todos los campos son obligatorios");
            return;
        }

        // Verifica que las contraseñas no coincidan
        if (!password.equals(confirmPassword)) {
            validationError.setValue("Las contraseñas no coinciden");
            return;
        }

        // Verifica que el correo tenga un formato no válido
        if (!email.contains("@")) {
            validationError.setValue("Formato de correo electrónico inválido");
            return;
        }

        // Verifica que la contraseña tenga menos de 6 caracteres
        if (password.length() < 6) {
            validationError.setValue("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        Log.d(TAG, "Validación exitosa, procediendo con el registro");

        // Llamada al repositorio para registrar al usuario
        User user = new User(null, fullName, email, phone, address);
        userRepository.registerUser(email, password, user)
                .observeForever(success -> {
                    Log.d(TAG, "Resultado del registro: " + success);
                    registrationResult.setValue(success);
                });
    }
}
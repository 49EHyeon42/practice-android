package dev.ehyeon.androidexampleapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public LiveData<List<User>> findAllUserToLiveData() {
        return userRepository.findAllUserToLiveData();
    }

    public void saveUser(String email, String name) {
        userRepository.save(email, name);
    }
}

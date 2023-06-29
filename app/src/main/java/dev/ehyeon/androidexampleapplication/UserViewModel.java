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

    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    public void saveUser(UserDto userDto) {
        userRepository.saveUser(userDto);
    }

    public LiveData<List<User>> findAllUserToLiveData() {
        return userRepository.findAllUserToLiveData();
    }
}

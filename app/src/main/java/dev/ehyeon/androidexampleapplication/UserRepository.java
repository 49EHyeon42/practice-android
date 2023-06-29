package dev.ehyeon.androidexampleapplication;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserById(Long id) {
        List<User> users = userDao.selectUserById(id);
        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> findAllUser() {
        return userDao.selectAllUser();
    }

    public void saveUser(UserDto userDto) {
        User user = new User();
        user.email = userDto.getEmail();
        user.name = userDto.getName();

        userDao.insertUser(user);
    }

    public void deleteAllUser() {
        userDao.deleteAllUser();
    }

    public LiveData<List<User>> findAllUserToLiveData() {
        return userDao.selectAllUserToLiveData();
    }

    public int getCount() {
        return userDao.count();
    }
}

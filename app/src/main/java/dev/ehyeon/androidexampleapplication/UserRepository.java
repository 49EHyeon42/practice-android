package dev.ehyeon.androidexampleapplication;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private final UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(String email, String name) {
        User user = new User();
        user.email = email;
        user.name = name;

        userDao.insertUser(user);
    }

    public User findUserById(Long id) {
        List<User> users = userDao.selectUserById(id);
        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> findAllUser() {
        return userDao.selectAllUser();
    }

    public LiveData<List<User>> findAllUserToLiveData() {
        return userDao.selectAllUserToLiveData();
    }

    public void deleteAllUser() {
        userDao.deleteAllUser();
    }

    public int getCount() {
        return userDao.count();
    }
}

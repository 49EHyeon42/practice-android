package dev.ehyeon.androidexampleapplication;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final List<User> storage;

    public UserRepository() {
        storage = new ArrayList<>();
    }

    public void save(String email, String name) {
        storage.add(new User(email, name));
    }

    public int getCount() {
        return storage.size();
    }

    public User findUserByPosition(int position) {
        return storage.get(position);
    }
}

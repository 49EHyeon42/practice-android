package dev.ehyeon.androidexampleapplication;

import android.app.Application;

import androidx.room.Room;

public class EHyeonApplication extends Application {

    private UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        EHyeonDatabase database = Room
                .databaseBuilder(this, EHyeonDatabase.class, "EHyeon Database")
                .allowMainThreadQueries() // TODO 권장하지 않는 방법, refactor
                .build();

        userRepository = new UserRepository(database.userDao());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

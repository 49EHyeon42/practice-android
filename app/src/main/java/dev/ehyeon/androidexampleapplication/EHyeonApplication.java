package dev.ehyeon.androidexampleapplication;

import android.app.Application;

public class EHyeonApplication extends Application {

    private UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        EHyeonComponent component = DaggerEHyeonComponent.builder()
                .userModule(new UserModule(getApplicationContext()))
                .build();

        userRepository = new UserRepository(component.getUserDao());
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}

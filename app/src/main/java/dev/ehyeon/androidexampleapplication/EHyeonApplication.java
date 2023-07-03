package dev.ehyeon.androidexampleapplication;

import android.app.Application;

import dev.ehyeon.androidexampleapplication.di.DaggerEHyeonComponent;
import dev.ehyeon.androidexampleapplication.di.DbModule;
import dev.ehyeon.androidexampleapplication.di.EHyeonComponent;
import dev.ehyeon.androidexampleapplication.di.UserModule;

public class EHyeonApplication extends Application {

    private EHyeonComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerEHyeonComponent.builder()
                .dbModule(new DbModule(this))
                .userModule(new UserModule())
                .build();
    }

    public EHyeonComponent getComponent() {
        return component;
    }
}

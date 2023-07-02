package dev.ehyeon.androidexampleapplication;

import android.app.Application;

public class EHyeonApplication extends Application {

    private EHyeonComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerEHyeonComponent.builder()
                .dbModule(new DbModule(this))
                .build();
    }

    public EHyeonComponent getComponent() {
        return component;
    }
}

package dev.ehyeon.androidexampleapplication;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DbModule.class})
@Singleton
public interface EHyeonComponent {

    void inject(MainActivity mainActivity);
}

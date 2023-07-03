package dev.ehyeon.androidexampleapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import dev.ehyeon.androidexampleapplication.presentation.MainActivity;

@Component(modules = {DbModule.class, UserModule.class})
@Singleton
public interface EHyeonComponent {

    void inject(MainActivity mainActivity);
}

package dev.ehyeon.androidexampleapplication.di;

import javax.inject.Singleton;

import dagger.Component;
import dev.ehyeon.androidexampleapplication.presentation.DaggerFragment;

@Component(modules = {DbModule.class, UserModule.class})
@Singleton
public interface EHyeonComponent {

    void inject(DaggerFragment daggerFragment);
}

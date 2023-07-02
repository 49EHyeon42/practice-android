package dev.ehyeon.androidexampleapplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @Singleton
    public UserRepository provideUserRepository(EHyeonDatabase eHyeonDatabase) {
        return new UserRepository(eHyeonDatabase.userDao());
    }

    @Provides
    @Singleton
    public UserViewModelFactory provideUserViewModelFactory(UserRepository userRepository) {
        return new UserViewModelFactory(userRepository);
    }
}

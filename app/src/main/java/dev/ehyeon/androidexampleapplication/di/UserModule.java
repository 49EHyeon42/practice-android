package dev.ehyeon.androidexampleapplication.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.ehyeon.androidexampleapplication.presentation.UserViewModelFactory;
import dev.ehyeon.androidexampleapplication.data.common.EHyeonDatabase;
import dev.ehyeon.androidexampleapplication.data.user.UserRepository;

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

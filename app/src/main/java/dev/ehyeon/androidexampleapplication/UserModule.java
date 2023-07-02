package dev.ehyeon.androidexampleapplication;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    private final Context context;

    public UserModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    public EHyeonDatabase provideEHyeonDatabase(Context context) {
        return EHyeonDatabase.getInstance(context);
    }

    @Provides
    public UserDao provideUserDao(EHyeonDatabase eHyeonDatabase) {
        return eHyeonDatabase.userDao();
    }

    @Provides
    public UserRepository provideUserRepository(UserDao userDao) {
        return new UserRepository(userDao);
    }

    @Provides
    public UserViewModelFactory provideUserViewModelFactory(UserRepository userRepository) {
        return new UserViewModelFactory(userRepository);
    }
}

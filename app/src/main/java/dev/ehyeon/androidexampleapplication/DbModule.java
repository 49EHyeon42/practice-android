package dev.ehyeon.androidexampleapplication;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private final Context context;

    public DbModule(Application application) {
        context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    public EHyeonDatabase provideEHyeonDatabase() {
        return Room
                .databaseBuilder(context, EHyeonDatabase.class, "EHyeonDatabase")
                .allowMainThreadQueries()
                .build();
    }

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

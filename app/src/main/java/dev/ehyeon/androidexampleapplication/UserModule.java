package dev.ehyeon.androidexampleapplication;

import android.content.Context;

import androidx.room.Room;

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
        return Room
                .databaseBuilder(context, EHyeonDatabase.class, "EHyeon Database")
                .allowMainThreadQueries() // TODO 권장하지 않는 방법, refactor
                .build();
    }

    @Provides
    public UserDao provideUserDao(EHyeonDatabase eHyeonDatabase) {
        return eHyeonDatabase.userDao();
    }
}

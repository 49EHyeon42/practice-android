package dev.ehyeon.androidexampleapplication.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.ehyeon.androidexampleapplication.data.common.EHyeonDatabase;

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
                .allowMainThreadQueries() // TODO 권장하지 않는 방법, refactor
                .build();
    }
}

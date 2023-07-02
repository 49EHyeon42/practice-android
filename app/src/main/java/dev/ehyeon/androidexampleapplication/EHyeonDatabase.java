package dev.ehyeon.androidexampleapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class EHyeonDatabase extends RoomDatabase {

    public static EHyeonDatabase instance;

    public static EHyeonDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(context, EHyeonDatabase.class, "EHyeon Database")
                    .allowMainThreadQueries() // TODO 권장하지 않는 방법, refactor
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}

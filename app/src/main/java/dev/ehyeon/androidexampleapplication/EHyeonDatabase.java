package dev.ehyeon.androidexampleapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class EHyeonDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}

package dev.ehyeon.androidexampleapplication.data.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.ehyeon.androidexampleapplication.data.user.User;
import dev.ehyeon.androidexampleapplication.data.user.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class EHyeonDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}

package dev.ehyeon.androidexampleapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao { // TODO fix select, insert, update, delete 순서로

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUser(User user);

    @Query("SELECT * FROM user WHERE id = :id")
    List<User> selectUserById(long id);

    @Query("SELECT * FROM user")
    List<User> selectAllUser();

    @Query("SELECT * FROM user")
    LiveData<List<User>> selectAllUserToLiveData();

    @Query("DELETE FROM user")
    void deleteAllUser();

    @Query("SELECT COUNT(*) FROM user")
    int count();
}

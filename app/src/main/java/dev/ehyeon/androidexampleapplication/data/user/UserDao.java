package dev.ehyeon.androidexampleapplication.data.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    // SELECT
    @Query("SELECT * FROM user WHERE id = :id")
    List<User> selectUserById(long id);

    @Query("SELECT * FROM user")
    List<User> selectAllUser();

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUser(User user);

    // UPDATE

    // DELETE
    @Query("DELETE FROM user")
    void deleteAllUser();

    // etc.
    @Query("SELECT * FROM user")
    LiveData<List<User>> selectAllUserToLiveData();
}

package dev.ehyeon.androidexampleapplication.data.user;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String email;
    public String name;
}

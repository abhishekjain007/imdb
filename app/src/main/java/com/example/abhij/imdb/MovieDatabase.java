package com.example.abhij.imdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by abhij on 25-03-2018.
 */

@Database(entities = {Movie.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase{


    abstract UserDAO getuserDAO();


}

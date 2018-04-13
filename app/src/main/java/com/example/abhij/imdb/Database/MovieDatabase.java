package com.example.abhij.imdb.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.abhij.imdb.Movie;

/**
 * Created by abhij on 25-03-2018.
 */

@Database(entities = {Movie.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase{


    public abstract UserDAO getuserDAO();


}

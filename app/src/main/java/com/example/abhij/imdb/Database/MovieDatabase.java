package com.example.abhij.imdb.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.ShowsClasses.Show;

/**
 * Created by abhij on 25-03-2018.
 */


@Database(entities = {Movie.class,Show.class},version = 1,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase{


    public abstract UserDAO getuserDAO();


}

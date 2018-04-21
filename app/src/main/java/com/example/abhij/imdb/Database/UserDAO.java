package com.example.abhij.imdb.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.ShowsClasses.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhij on 25-03-2018.
 */

@Dao
public interface UserDAO  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(Movie movie);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(ArrayList<Movie> movies);

    @Delete
    void deleteMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovie(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShow(Show show);

    @Query("SELECT * FROM shows")
    List<Show> getAllShowes();

    @Delete
    void deleteShow(Show show);


}

package com.example.abhij.imdb.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.abhij.imdb.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhij on 25-03-2018.
 */

@Dao
public interface UserDAO  {

    @Insert
    void insertMovie(Movie movie);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(ArrayList<Movie> movies);

    @Delete
    void deleteMovie(Movie movie);

    @Update
    void updateMovie(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMoives();

    @Query("SELECT * FROM movies WHERE id = :id")
    Movie getMovie(int id);


}

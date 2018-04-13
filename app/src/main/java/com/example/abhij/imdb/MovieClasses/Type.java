package com.example.abhij.imdb.MovieClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by abhij on 12-04-2018.
 */

@Entity(tableName ="genres",
         foreignKeys = {@ForeignKey(entity = DifferentMovies.class, parentColumns ="id" , childColumns = "movieId")}
         ,primaryKeys = {"id"})

public class Type {


    private int id;
    private String genre;
    private int movieId;


}

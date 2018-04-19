package com.example.abhij.imdb.MovieClasses;

import java.util.ArrayList;

/**
 * Created by abhij on 25-03-2018.
 */

public class DifferentMovies {

    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Movie> results;


    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}

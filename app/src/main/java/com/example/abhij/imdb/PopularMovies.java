package com.example.abhij.imdb;

import java.util.ArrayList;

/**
 * Created by abhij on 25-03-2018.
 */

public class PopularMovies {

    private int page;
    private int total_results;
    private int total_pages;
    private ArrayList<Movie> results;

    public PopularMovies(int page, int total_results, int total_pages, ArrayList<Movie> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }
}

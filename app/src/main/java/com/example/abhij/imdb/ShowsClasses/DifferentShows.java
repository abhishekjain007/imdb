package com.example.abhij.imdb.ShowsClasses;

import java.util.ArrayList;

/**
 * Created by abhij on 19-04-2018.
 */

public class DifferentShows {

    public int page;
    public int total_results;
    public int total_pages;
    public ArrayList<Show> results;




    public ArrayList<Show> getResults() {
        return results;
    }

    public void setResults(ArrayList<Show> results) {
        this.results = results;
    }
}

package com.example.abhij.imdb;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by abhij on 25-03-2018.
 */

public class Movie {

    private int id;
    private int imdb_id;
    private String title;
    private Boolean video;
    private String original_title;
    private String poster_path;
    private String backdrop_path;
    private String original_language;
    private Date release_date;
    private String overview;

    private ArrayList<genres> genres;


    class genres{
        private int id;
        private String name;
    }

    public Movie(int id, int imdb_id, String title, Boolean video, String original_title, String poster_path, String backdrop_path, String original_language, Date release_date, String overview, ArrayList<Movie.genres> genres) {
        this.id = id;
        this.imdb_id = imdb_id;
        this.title = title;
        this.video = video;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.release_date = release_date;
        this.overview = overview;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public int getImdb_id() {
        return imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getVideo() {
        return video;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public ArrayList<Movie.genres> getGenres() {
        return genres;
    }
}

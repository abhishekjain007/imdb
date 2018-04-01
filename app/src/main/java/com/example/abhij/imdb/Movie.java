package com.example.abhij.imdb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by abhij on 25-03-2018.
 */

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    private int id;

    private int imdb_id;
    private String title;
    private Boolean video;
    private String original_title;
    private String poster_path;
    private String backdrop_path;
    private String original_language;
    private String release_date;
    private String overview;


    @Ignore
    private ArrayList<genres> genres;



    class genres{
        int id;
        String name;
    }

    public Movie(int id, int imdb_id, String title, Boolean video, String original_title, String poster_path, String backdrop_path, String original_language, String release_date, String overview, ArrayList<Movie.genres> genres) {
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


    public Movie() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(int imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public ArrayList<Movie.genres> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Movie.genres> genres) {
        this.genres = genres;
    }
}

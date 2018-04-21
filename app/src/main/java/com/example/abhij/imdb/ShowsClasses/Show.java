package com.example.abhij.imdb.ShowsClasses;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by abhij on 18-04-2018.
 */
@Entity(tableName = "shows")
public class Show {

    public String original_name;

    @Ignore
    public ArrayList<Integer> genre_ids;
    public String name;
    public float popularity;

    @Ignore
    public ArrayList<String> origin_country;
    public String first_air_date;
    public String backdrop_path;
    public String original_language;

    @PrimaryKey
    public int id;
    public float vote_average;
    public String overview;
    public String poster_path;
    String number_of_episodes;
    String number_of_seasons;

    public Show(String original_name, String name, float popularity, String first_air_date, String backdrop_path, String original_language, int id, float vote_average, String overview, String poster_path, String number_of_episodes, String number_of_seasons) {
        this.original_name = original_name;
        this.name = name;
        this.popularity = popularity;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
    }

    public Show(String original_name, ArrayList<Integer> genre_ids, String name, float popularity, ArrayList<String> origin_country, String first_air_date, String backdrop_path, String original_language, int id, float vote_average, String overview, String poster_path) {
        this.original_name = original_name;
        this.genre_ids = genre_ids;
        this.name = name;
        this.popularity = popularity;
        this.origin_country = origin_country;
        this.first_air_date = first_air_date;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }


    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public ArrayList<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(ArrayList<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public ArrayList<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(ArrayList<String> origin_country) {
        this.origin_country = origin_country;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(String number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public String getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(String number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }
}

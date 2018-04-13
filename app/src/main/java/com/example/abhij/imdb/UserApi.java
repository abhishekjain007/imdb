package com.example.abhij.imdb;

import com.example.abhij.imdb.MovieClasses.DifferentMovies;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abhij on 25-03-2018.
 */

public interface UserApi {


    @GET("movie/popular?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")  //@GET('/')
    public Call<DifferentMovies> getPopularMovies();

    @GET("movie/latest?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")
    public Call<DifferentMovies> getLatestMovies();

    @GET("movie/now_playing?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")
    public Call<DifferentMovies> getNowPlayingMovies();

    @GET("movie/top_rated?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")
    public Call<DifferentMovies> getTopRatedMovies();

    @GET("movie/upcoming?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")
    public Call<DifferentMovies> getUpcomingMovies();

}

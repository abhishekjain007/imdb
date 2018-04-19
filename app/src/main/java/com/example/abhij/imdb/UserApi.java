package com.example.abhij.imdb;

import com.example.abhij.imdb.MovieClasses.CastFormat;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieClasses.TrailerFormat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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

    @GET("movie/{movie_id}/credits?api_key=815982ecec1c306def1a2852911c727c")
    public Call<CastFormat> getCast(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/videos?api_key=815982ecec1c306def1a2852911c727c&language=en-US")
    public Call<TrailerFormat> getTrailers(@Path("movie_id") int movie_id);

    @GET("movie/{movie_id}/similar?api_key=815982ecec1c306def1a2852911c727c&language=en-US&page=1")
    public Call<DifferentMovies> getSimilarMovies(@Path("movie_id") int movie_id);


}

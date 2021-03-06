package com.example.abhij.imdb;

import com.example.abhij.imdb.MovieClasses.CastFormat;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieClasses.TrailerFormat;
import com.example.abhij.imdb.ShowsClasses.DifferentShows;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abhij on 25-03-2018.
 */

public interface UserApi {


    @GET("movie/popular?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getPopularMovies();

    @GET("movie/latest?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getLatestMovies();

    @GET("movie/now_playing?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getNowPlayingMovies();

    @GET("movie/top_rated?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getTopRatedMovies();

    @GET("movie/upcoming?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getUpcomingMovies();

    @GET("{type}/{id}/credits?api_key=815982ecec1c306def1a2852911c727c")
    Call<CastFormat> getCast(@Path("type") String type, @Path("id") int id);

    @GET("{type}/{id}/videos?api_key=815982ecec1c306def1a2852911c727c")
    Call<TrailerFormat> getTrailers(@Path("type") String type, @Path("id") int id);

    @GET("movie/{id}/similar?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentMovies> getSimilarMovies(@Path("id") int id);

    @GET("tv/{tv_type}?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentShows> getTv_shows(@Path("tv_type") String tv_type);//@Query("api_key") String query );

    @GET("tv/{tv_id}/similar?api_key=815982ecec1c306def1a2852911c727c")
    Call<DifferentShows> getSimilarShows(@Path("tv_id") int tv_id);

}

package com.example.abhij.imdb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abhij on 25-03-2018.
 */

public interface UserApi {


    @GET("movie/popular?api_key=815982ecec1c306def1a2852911c727c")
    public Call<PopularMovies> getPopularMovies();


}

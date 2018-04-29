package com.example.abhij.imdb;

import android.os.AsyncTask;
import android.util.Log;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieClasses.Movie;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by abhij on 13-04-2018.
 */

public class MovieFetching_AsynTask extends AsyncTask{

    public interface MovieDownloadListener{

        void OnDownloadComplete(ArrayList<Movie> movies);
    }

    private MovieDownloadListener listener;


    MyApi myApi;
    ArrayList<Movie> movies_list;
    int option ;


    public MovieFetching_AsynTask(int option,MovieDownloadListener listener )
    {
        this.listener= listener;
        this.option= option;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        myApi = MyApi.getInstance();
        UserApi userApi = myApi.getUserApi();

        Log.d("background",option+"");

        Call<DifferentMovies> call;
        switch(option){
            case 1:  call = userApi.getPopularMovies();
                      break;
            case 3: call = userApi.getTopRatedMovies();
                       break;
            case 4: call = userApi.getNowPlayingMovies();
                       break;
            case 5: call = userApi.getUpcomingMovies();
                       break;
            default: call = userApi.getPopularMovies();
                     break;

        }


//        call.enqueue(new Callback<DifferentMovies>() {
//            @Override
//            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {
//
//
//                DifferentMovies differentMovies = response.body();
//                movies_list= differentMovies.getResults();
//
//                Log.d("movies","got movies");
//                onPostExecute(movies_list);
//            }
//
//
//            @Override
//            public void onFailure(Call<DifferentMovies> call, Throwable t) {
//
//                return ;
//            }
//        });



        try {
            Response<DifferentMovies> response = call.execute();
            DifferentMovies differentMovies = response.body();
            movies_list= differentMovies.getResults();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies_list;
    }


    @Override
    protected void onPostExecute(Object o) {

        super.onPostExecute(o);
        listener.OnDownloadComplete(movies_list);
    }
}

package com.example.abhij.imdb.Activities;

import android.arch.persistence.room.Room;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.abhij.imdb.Database.MovieDatabase;
import com.example.abhij.imdb.Database.UserDAO;
import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MyApi;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.UserApi;
import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_Large;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MyApi myApi;
    ArrayList<Movie> movies_list;
    RecyclerView recyclerView_list;
    UserRecyclerAdapter_Large adapter ;
    MovieDatabase database ;
    UserDAO userDAO;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myApi = MyApi.getInstance();
        UserApi userApi = myApi.getUserApi();
        movies_list=new ArrayList<>();
        recyclerView_list = (RecyclerView) findViewById(R.id.recyclerList_In_Large);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe);

        database= Room.databaseBuilder(this,MovieDatabase.class,"movieDatabase")
                .allowMainThreadQueries()
                .build();

        userDAO = database.getuserDAO();



        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) userDAO.getAllMoives();

        movies_list.clear();
        movies_list.addAll(movieArrayList);
        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(true);
        Call<DifferentMovies> call = userApi.getPopularMovies();
        call.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {


                DifferentMovies differentMovies = response.body();
                movies_list.clear();
                movies_list.addAll(differentMovies.getResults());
                adapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(true);
                userDAO.insertMovies(movies_list);

            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {

            }
        });




    }







}

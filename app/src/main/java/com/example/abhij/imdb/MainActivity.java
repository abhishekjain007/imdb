package com.example.abhij.imdb;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    OpenHelper openHelper;
    ArrayList<Movie> movies_list;
    RecyclerView recyclerView_list;
    UserRecyclerAdapter adapter ;
    MovieDatabase database ;
    UserDAO userDAO;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = OpenHelper.getInstance();
        UserApi userApi = openHelper.getUserApi();
        movies_list=new ArrayList<>();
        recyclerView_list = (RecyclerView) findViewById(R.id.recyclerList_Movies);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe);

        database= Room.databaseBuilder(this,MovieDatabase.class,"movieDatabase")
                .allowMainThreadQueries()
                .build();

        userDAO = database.getuserDAO();


        adapter = new UserRecyclerAdapter(this, movies_list, new UserRecyclerAdapter.OnItemClicked() {
            @Override
            public void onClick(int position) {

            }
        });

        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView_list.setAdapter(adapter);

        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) userDAO.getAllMoives();
        movies_list.clear();
        movies_list.addAll(movieArrayList);
        adapter.notifyDataSetChanged();

        swipeRefreshLayout.setRefreshing(true);
        Call<PopularMovies> call = userApi.getPopularMovies();
        call.enqueue(new Callback<PopularMovies>() {
           @Override
           public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {


               PopularMovies popularMovies = response.body();
               movies_list.clear();
               movies_list.addAll(popularMovies.getResults());
               adapter.notifyDataSetChanged();

               swipeRefreshLayout.setRefreshing(true);
               userDAO.insertMovies(movies_list);

           }

           @Override
           public void onFailure(Call<PopularMovies> call, Throwable t) {

           }
       });




    }







}

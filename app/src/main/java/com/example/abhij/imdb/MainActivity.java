package com.example.abhij.imdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openHelper = OpenHelper.getInstance();
        UserApi userApi = openHelper.getUserApi();
        movies_list=new ArrayList<>();
        recyclerView_list = (RecyclerView) findViewById(R.id.recyclerList_Movies);

        adapter = new UserRecyclerAdapter(this, movies_list, new UserRecyclerAdapter.OnItemClicked() {
            @Override
            public void onClick(int position) {

            }
        });

        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView_list.setAdapter(adapter);

        Call<PopularMovies> call = userApi.getPopularMovies();
        call.enqueue(new Callback<PopularMovies>() {
           @Override
           public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
               PopularMovies popularMovies = response.body();
               movies_list.addAll(popularMovies.getResults());
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onFailure(Call<PopularMovies> call, Throwable t) {

           }
       });




    }







}

package com.example.abhij.imdb;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abhij.imdb.Activities.MainActivity2;
import com.example.abhij.imdb.Database.MovieDatabase;
import com.example.abhij.imdb.Database.UserDAO;
import com.example.abhij.imdb.Fragments.LargeList_Fragment;
import com.example.abhij.imdb.Fragments.SmallList_Fragment;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieClasses.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Movies extends Fragment{


    public Fragment_Movies() {

    }

    ArrayList<Movie> movies_popular=new ArrayList<>();
    ArrayList<Movie> movies_topRated=new ArrayList<>();
    ArrayList<Movie> movies_nowPlaying=new ArrayList<>();
    ArrayList<Movie> movies_upcoming=new ArrayList<>();


    Bundle bundle1,bundle2,bundle3,bundle4;

    FragmentManager manager ;



    LargeList_Fragment fragment_popular;
    SmallList_Fragment fragment_topRated;
    LargeList_Fragment fragment_nowPlaying;
    SmallList_Fragment fragment_upcoming;

    MovieDatabase database ;
    UserDAO userDAO;
    SwipeRefreshLayout swipeRefreshLayout;

    MyApi myApi;
   public  UserApi userApi;
    int option ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_fragment__movies, container, false);

        fragment_popular = new LargeList_Fragment() ;
        fragment_topRated= new SmallList_Fragment();
        fragment_nowPlaying = new LargeList_Fragment() ;
        fragment_upcoming = new SmallList_Fragment();
        bundle1 = new Bundle();
        bundle2 = new Bundle();
        bundle3 = new Bundle();
        bundle4 = new Bundle();


        manager = getActivity().getSupportFragmentManager();

        myApi = MyApi.getInstance();
        userApi = myApi.getUserApi();

        database = Room.databaseBuilder(getActivity().getApplicationContext(),MovieDatabase.class,"movieDatabase")
                .allowMainThreadQueries()
                .build();
        userDAO= database.getuserDAO();

        Log.d("background",option+"");


        fetchPopularMovies();
        fetchTopRatedMovies();
        fetchNowPlayingMovies();
        fetchUpcomingMovies();

        return view;
    }




    private void fetchPopularMovies() {
        Call<DifferentMovies> call= userApi.getPopularMovies();
        call.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {


                DifferentMovies differentMovies = response.body();
                movies_popular.clear();
                movies_popular= differentMovies.getResults();

                bundle1.putSerializable("moviesList",movies_popular);
                bundle1.putString("code","movie");
                fragment_popular.setArguments(bundle1);

                fragment_popular.setUpListener(new LargeList_Fragment.OnClickListener() {
                    @Override
                    public void onClick(int position, Object object) {
                        onClickItem(position,object);
                    }
                }, new LargeList_Fragment.OnHeartClicked() {
                    @Override
                    public void onHeartClick(int position, Object object) {
                        OnHeartClick(position,object);
                    }
                });

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_popularMovies,fragment_popular).commit();

            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {


                Toast.makeText(getActivity(),"Could Not Show Popular ",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchTopRatedMovies() {

        Call<DifferentMovies> call2= userApi.getTopRatedMovies();
        call2.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_topRated.clear();
                movies_topRated= differentMovies.getResults();

                bundle2.putSerializable("moviesList",movies_topRated);
                bundle2.putString("code","movie");
                fragment_topRated.setArguments(bundle2);

                fragment_topRated.setUpListener(new SmallList_Fragment.OnClickListener() {
                    @Override
                    public void onClick(int position, Object object) {
                        onClickItem(position, object);
                    }
                }, new SmallList_Fragment.OnHeartClicked() {
                    @Override
                    public void onHeartClick(int position, Object object) {
                        OnHeartClick(position,object);
                    }
                });

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_topRatedMovies,fragment_topRated).commit();

            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {


                Toast.makeText(getActivity(),"Could Not Show top Rated",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchNowPlayingMovies() {
        Call<DifferentMovies> call3= userApi.getNowPlayingMovies();
        call3.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_nowPlaying.clear();
                movies_nowPlaying= differentMovies.getResults();

                bundle3.putSerializable("moviesList",movies_nowPlaying);
                bundle3.putString("code","movie");
                fragment_nowPlaying.setArguments(bundle3);

                fragment_nowPlaying.setUpListener(new LargeList_Fragment.OnClickListener() {
                    @Override
                    public void onClick(int position, Object object) {
                        onClickItem(position,object);
                    }
                }, new LargeList_Fragment.OnHeartClicked() {
                    @Override
                    public void onHeartClick(int position, Object object) {
                        OnHeartClick(position,object);
                    }
                });

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_nowPlayingMovies,fragment_nowPlaying).commit();


            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {

                Toast.makeText(getActivity(),"Could Not Show Now Playing",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchUpcomingMovies() {
        Call<DifferentMovies> call4= userApi.getUpcomingMovies();
        call4.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_upcoming.clear();
                movies_upcoming= differentMovies.getResults();

                bundle4.putSerializable("moviesList",movies_upcoming);
                bundle4.putString("code","movie");
                fragment_upcoming.setArguments(bundle4);

                fragment_upcoming.setUpListener(new SmallList_Fragment.OnClickListener() {
                    @Override
                    public void onClick(int position, Object object) {
                        onClickItem(position,object);
                    }
                }, new SmallList_Fragment.OnHeartClicked() {
                    @Override
                    public void onHeartClick(int position, Object object) {
                        OnHeartClick(position,object);
                    }
                });

                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.container_upcomingMovies,fragment_upcoming).commit();



            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {

                Toast.makeText(getActivity(),"Could Not Show Upcoming Movies",Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void onClickItem(int position, Object object) {
            Movie movie= (Movie)object;

            Bundle bundle =new Bundle();
            bundle.putString("poster",movie.getPoster_path());
            bundle.putInt("movie_id",movie.getId());
            bundle.putString("overView",movie.getOverview());

            Fragment_Details fragment=new Fragment_Details();
            fragment.setArguments(bundle);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container_main,fragment).commit();
        }

    private void OnHeartClick(int position, Object object) {

        userDAO.insertMovie((Movie)object);

    }
}

package com.example.abhij.imdb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abhij.imdb.Database.MovieDatabase;
import com.example.abhij.imdb.Database.UserDAO;
import com.example.abhij.imdb.Fragments.LargeList_Fragment;
import com.example.abhij.imdb.Fragments.SmallList_Fragment;
import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieDetail;
import com.example.abhij.imdb.MyApi;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.UserApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SmallList_Fragment.OnClickListener
        ,LargeList_Fragment.OnClickListener{


    ArrayList<Movie> movies_popular;
    ArrayList<Movie> movies_topRated;
    ArrayList<Movie> movies_nowPlaying;
    ArrayList<Movie> movies_upcoming;


    Bundle bundle;


    LargeList_Fragment fragment_popular;
    SmallList_Fragment fragment_topRated;
    LargeList_Fragment fragment_nowPlaying;
    SmallList_Fragment fragment_upcoming;

    MovieDatabase database ;
    UserDAO userDAO;
    SwipeRefreshLayout swipeRefreshLayout;

    MyApi myApi;
    UserApi userApi;
    int option ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fragment_popular = new LargeList_Fragment() ;
        fragment_topRated= new SmallList_Fragment();
        fragment_nowPlaying = new LargeList_Fragment() ;
        fragment_upcoming = new SmallList_Fragment();
        bundle = new Bundle();


        myApi = MyApi.getInstance();
        userApi = myApi.getUserApi();

        Log.d("background",option+"");


        fetchPopularMovies();
        fetchTopRatedMovies();
        fetchNowPlayingMovies();
        fetchUpcomingMovies();



//        recyclerView_list = (RecyclerView) findViewById(R.id.recyclerList_In_Large);
//        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe);
//
//        database= Room.databaseBuilder(this,MovieDatabase.class,"movieDatabase")
//                .allowMainThreadQueries()
//                .build();
//
//        userDAO = database.getuserDAO();
//
//
//
//        ArrayList<Movie> movieArrayList = (ArrayList<Movie>) userDAO.getAllMoives();
//
//        movies_list.clear();
//        movies_list.addAll(movieArrayList);
//        adapter.notifyDataSetChanged();

//        swipeRefreshLayout.setRefreshing(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



     private void fetchPopularMovies() {
        Call<DifferentMovies> call= userApi.getPopularMovies();
        call.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {


                DifferentMovies differentMovies = response.body();
                movies_popular= differentMovies.getResults();

                bundle.putSerializable("moviesList",movies_popular);
                bundle.putString("code","movie");
                fragment_popular.setArguments(bundle);



                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.container_popularMovies,fragment_popular).commit();
            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {


                Toast.makeText(MainActivity2.this,"Could Not Show Popular ",Toast.LENGTH_SHORT);
            }
        });

    }


    private void fetchTopRatedMovies() {

        Call<DifferentMovies> call2= userApi.getTopRatedMovies();
        call2.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_topRated= differentMovies.getResults();

                bundle.putSerializable("moviesList",movies_topRated);
                bundle.putString("code","movie");
                fragment_topRated.setArguments(bundle);


                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.container_topRatedMovies,fragment_topRated).commit();
            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {


                Toast.makeText(MainActivity2.this,"Could Not Show top Rated",Toast.LENGTH_SHORT);
            }
        });

    }


    private void fetchNowPlayingMovies() {
        Call<DifferentMovies> call3= userApi.getNowPlayingMovies();
        call3.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_nowPlaying= differentMovies.getResults();

                bundle.putSerializable("moviesList",movies_nowPlaying);
                bundle.putString("code","movie");
                fragment_nowPlaying.setArguments(bundle);


                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.container_nowPlayingMovies,fragment_nowPlaying).commit();
            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {

                Toast.makeText(MainActivity2.this,"Could Not Show Now Playing",Toast.LENGTH_SHORT);
            }
        });

    }

    private void fetchUpcomingMovies() {
        Call<DifferentMovies> call4= userApi.getUpcomingMovies();
        call4.enqueue(new Callback<DifferentMovies>() {
            @Override
            public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {

                DifferentMovies differentMovies = response.body();
                movies_upcoming= differentMovies.getResults();

                bundle.putSerializable("moviesList",movies_upcoming);
                bundle.putString("code","movie");
                fragment_upcoming.setArguments(bundle);

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.container_upcomingMovies,fragment_upcoming).commit();
            }

            @Override
            public void onFailure(Call<DifferentMovies> call, Throwable t) {

                Toast.makeText(MainActivity2.this,"Could Not Show Upcoming Movies",Toast.LENGTH_SHORT);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(int position,Object object) {
        Movie movie= (Movie)object;
        Intent intent =new Intent(this, MovieDetail.class);
        Bundle bundle =new Bundle();
        bundle.putString("poster",movie.getPoster_path());
        bundle.putInt("movie_id",movie.getId());
        bundle.putString("overView",movie.getOverview());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

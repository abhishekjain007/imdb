package com.example.abhij.imdb;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_circle;
import com.example.abhij.imdb.Fragments.CircleList_Fragment;
import com.example.abhij.imdb.Fragments.LargeList_Fragment;
import com.example.abhij.imdb.Fragments.SmallList_Fragment;
import com.example.abhij.imdb.MovieClasses.Cast;
import com.example.abhij.imdb.MovieClasses.CastFormat;
import com.example.abhij.imdb.MovieClasses.DifferentMovies;
import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.Trailer;
import com.example.abhij.imdb.MovieClasses.TrailerFormat;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;

public class MovieDetail extends AppCompatActivity implements CircleList_Fragment.OnClickListener ,LargeList_Fragment.OnClickListener,SmallList_Fragment.OnClickListener {

    ImageView poster ;
    MyApi myApi;
    UserApi userApi;
    ArrayList<Cast> castArrayList;
    ArrayList<Trailer> trailerArrayList;
    ArrayList<Movie> similarMovieArrayList;

    CircleList_Fragment cast_fragment;
    LargeList_Fragment trailer_fragment;
    SmallList_Fragment similarMovie_fragment;

    TextView text_overView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        myApi = MyApi.getInstance();
        userApi=myApi.getUserApi();
        castArrayList =  new ArrayList<>();
        trailerArrayList= new ArrayList<>();
        similarMovieArrayList = new ArrayList<>();

        cast_fragment= new CircleList_Fragment();
        trailer_fragment = new LargeList_Fragment();
        similarMovie_fragment = new SmallList_Fragment();

        poster = (ImageView)findViewById(R.id.image_poster_InDisplay);
        text_overView = (TextView) findViewById(R.id.text_discription);

        Bundle bundle = getIntent().getExtras();

        int movie_id = bundle.getInt("movie_id",-1);
        String overView = bundle.getString("overView","");
        String poster_string = "http://image.tmdb.org/t/p/w342/" + bundle.getString("poster");

        Picasso.get().load(poster_string).into(poster);

        text_overView.setText(overView);
        fetchTrailers(movie_id);
        fetchCast(movie_id);
        fetchSimilarMovies(movie_id);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void fetchTrailers(int movie_id) {

        if(movie_id!=-1)
        {
            Call<TrailerFormat> call = userApi.getTrailers("movie",movie_id);
            call.enqueue(new Callback<TrailerFormat>() {
                @Override
                public void onResponse(Call<TrailerFormat> call, Response<TrailerFormat> response) {
                    TrailerFormat trailerFormat = response.body();

                    trailerArrayList.clear();
                    trailerArrayList.addAll(trailerFormat.results);

                    for(int i=0;i<trailerArrayList.size();) {
                        Trailer trailer = trailerArrayList.get(i);
                        if (trailer.getType().equals("Trailer") && (trailer.getSize() == 1080 || trailer.getSize() == 720)) {
                            i++;
                        } else {
                            trailerArrayList.remove(i);
                        }
                    }
                    if(trailerArrayList.size()!=0) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("trailerList", trailerArrayList);
                        bundle.putString("code", "trailer");
                        trailer_fragment.setArguments(bundle);

                        FragmentManager manager = getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.container_trailer, trailer_fragment).commit();
                    }
                }

                @Override
                public void onFailure(Call<TrailerFormat> call, Throwable t) {

                    Toast.makeText(MovieDetail.this,"Could not load trailers",Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void fetchCast(int movie_id) {

        if(movie_id!=-1){

            Call<CastFormat> call =userApi.getCast("movie",movie_id);
            call.enqueue(new Callback<CastFormat>() {
                @Override
                public void onResponse(Call<CastFormat> call, Response<CastFormat> response) {
                    CastFormat castFormat = response.body();


                    castArrayList.clear();
                    castArrayList .addAll(castFormat.cast);

                    for(int i=0;i<castArrayList.size();)
                    {
                        if(castArrayList.get(i).getProfile_path()==null)
                            castArrayList.remove(i);
                        else
                            i++;
                    }

                    Bundle bundle= new Bundle();
                    bundle.putSerializable("cast",castArrayList);
                    bundle.putString("code","cast");
                    cast_fragment.setArguments(bundle);

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction= manager.beginTransaction();

                    transaction.replace(R.id.container_cast,cast_fragment).commit();
                }

                @Override
                public void onFailure(Call<CastFormat> call, Throwable t) {
                    Toast.makeText(MovieDetail.this,"Could not fetch cast ",Toast.LENGTH_SHORT).show();
                }
            });

        }


    }


    private void fetchSimilarMovies(int movie_id) {

        if(movie_id!=-1)
        {
            Call<DifferentMovies> call = userApi.getSimilarMovies(movie_id);
            call.enqueue(new Callback<DifferentMovies>() {
                @Override
                public void onResponse(Call<DifferentMovies> call, Response<DifferentMovies> response) {
                    DifferentMovies differentMovies = response.body();

                    similarMovieArrayList.clear();
                    similarMovieArrayList.addAll(differentMovies.getResults());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("moviesList",similarMovieArrayList);
                    bundle.putString("code","movie");
                    similarMovie_fragment.setArguments(bundle);

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container_similarMovies,similarMovie_fragment).commit();

                }

                @Override
                public void onFailure(Call<DifferentMovies> call, Throwable t) {
                    Toast.makeText(MovieDetail.this,"Could not fetch cast ",Toast.LENGTH_SHORT).show();

                }
            });
        }





    }
    @Override
    public void OnClick(int position) {

    }

    @Override
    public void onClick(int position, Object object) {


        Trailer trailer = (Trailer)object;
         Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailer.getKey() + "&feature=youtu.be"));
            
            startActivity(appIntent);
        }

}

package com.example.abhij.imdb;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Details extends Fragment   {

    public Fragment_Details() {
    }


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment__details, container, false);



        myApi = MyApi.getInstance();
        userApi=myApi.getUserApi();
        castArrayList =  new ArrayList<>();
        trailerArrayList= new ArrayList<>();
        similarMovieArrayList = new ArrayList<>();

        cast_fragment= new CircleList_Fragment();
        trailer_fragment = new LargeList_Fragment();
        similarMovie_fragment = new SmallList_Fragment();

        poster = (ImageView)view.findViewById(R.id.image_poster_InDisplay);
        text_overView = (TextView)view. findViewById(R.id.text_discription);

        Bundle bundle = getArguments();

        int movie_id = bundle.getInt("movie_id",-1);
        String overView = bundle.getString("overView","");
        String poster_string = "http://image.tmdb.org/t/p/w342/" + bundle.getString("poster");

        Picasso.get().load(poster_string).into(poster);

        text_overView.setText(overView);
        fetchTrailers(movie_id);
        fetchCast(movie_id);
        fetchSimilarMovies(movie_id);

        return view;
    }

    private void fetchTrailers(int movie_id) {

        if(movie_id!=-1)
        {
            Call<TrailerFormat> call = userApi.getTrailers(movie_id);
            call.enqueue(new Callback<TrailerFormat>() {
                @Override
                public void onResponse(Call<TrailerFormat> call, Response<TrailerFormat> response) {
                    TrailerFormat trailerFormat = response.body();

                    trailerArrayList.clear();
                    if(trailerFormat.results!=null) {
                        trailerArrayList.addAll(trailerFormat.results);

                        for (int i = 0; i < trailerArrayList.size(); ) {
                            Trailer trailer = trailerArrayList.get(i);
                            if (trailer.getType().equals("Trailer") && (trailer.getSize() == 1080 || trailer.getSize() == 720)) {
                                i++;
                            } else {
                                trailerArrayList.remove(i);
                            }
                        }
                    }
                    if(trailerArrayList.size()!=0) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("trailerList", trailerArrayList);
                        bundle.putString("code", "trailer");
                        trailer_fragment.setArguments(bundle);

                        trailer_fragment.setUpListener(new LargeList_Fragment.OnClickListener() {
                            @Override
                            public void onClick(int position, Object object) {
                                Trailer trailer = (Trailer)object;
                                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + trailer.getKey() + "&feature=youtu.be"));

                                startActivity(appIntent);

                            }
                        });

                        FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.addToBackStack("trailer").replace(R.id.container_trailer, trailer_fragment).commit();

                    }
                }

                @Override
                public void onFailure(Call<TrailerFormat> call, Throwable t) {

                    Toast.makeText(getActivity(),"Could not load trailers",Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    private void fetchCast(int movie_id) {

        if(movie_id!=-1){

            Call<CastFormat> call =userApi.getCast(movie_id);
            call.enqueue(new Callback<CastFormat>() {
                @Override
                public void onResponse(Call<CastFormat> call, Response<CastFormat> response) {
                    CastFormat castFormat = response.body();


                    castArrayList.clear();
                    castArrayList.addAll(castFormat.cast);

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

                    cast_fragment.setUpListener(new CircleList_Fragment.OnClickListener() {
                        @Override
                        public void OnClick(int position) {

                        }
                    });

                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction= manager.beginTransaction();

                    transaction.replace(R.id.container_cast,cast_fragment).commit();
                }

                @Override
                public void onFailure(Call<CastFormat> call, Throwable t) {
                    Toast.makeText(getActivity(),"Could not fetch cast ",Toast.LENGTH_SHORT).show();
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

                    similarMovie_fragment.setUpListener(new SmallList_Fragment.OnClickListener() {
                        @Override
                        public void onClick(int position, Object object) {
                            onClickItem(position,object);

                        }
                    });

                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container_similarMovies,similarMovie_fragment).commit();

                }

                @Override
                public void onFailure(Call<DifferentMovies> call, Throwable t) {
                    Toast.makeText(getActivity(),"Could not fetch cast ",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    public void onClickItem(int position, Object object) {
        Movie movie= (Movie)object;

        Bundle bundle =new Bundle();
        bundle.putString("poster",movie.getPoster_path());
        bundle.putInt("movie_id",movie.getId());
        bundle.putString("overView",movie.getOverview());

        Fragment_Details fragment=new Fragment_Details();
        fragment.setArguments(bundle);

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main,fragment).commit();
    }
}

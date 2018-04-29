package com.example.abhij.imdb;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abhij.imdb.Database.MovieDatabase;
import com.example.abhij.imdb.Database.UserDAO;
import com.example.abhij.imdb.Fragments.LargeList_Fragment;
import com.example.abhij.imdb.Fragments.SmallList_Fragment;
import com.example.abhij.imdb.ShowsClasses.DifferentShows;
import com.example.abhij.imdb.ShowsClasses.Show;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Shows extends Fragment  {


    public Fragment_Shows() {
        // Required empty public constructor
    }

    ArrayList<Show> show_AiringToday = new ArrayList<>();
    ArrayList<Show> show_OnTheAir = new ArrayList<>();
    ArrayList<Show> show_popular = new ArrayList<>();
    ArrayList<Show> show_topRated = new ArrayList<>();


    Bundle bundle1, bundle2, bundle3, bundle4;

    FragmentManager manager;


    LargeList_Fragment fragment_airingToday;
    SmallList_Fragment fragment_onTheAir;
    LargeList_Fragment fragment_popular;
    SmallList_Fragment fragment_topRated;

    MovieDatabase database;
    UserDAO userDAO;
    SwipeRefreshLayout swipeRefreshLayout;

    MyApi myApi;
    UserApi userApi;
    int option;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__shows, container, false);

        fragment_airingToday = new LargeList_Fragment();
        fragment_onTheAir = new SmallList_Fragment();
        fragment_popular = new LargeList_Fragment();
        fragment_topRated = new SmallList_Fragment();

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
        userDAO = database.getuserDAO();

        fetchAiringToday();
        fetchOnTheAir();
        fetchPopular();
        fetchTopRated();


        return view;

    }

    private void fetchAiringToday() {
        Call<DifferentShows> call= userApi.getTv_shows("airing_today");
       call.enqueue(new Callback<DifferentShows>() {
           @Override
           public void onResponse(Call<DifferentShows> call, Response<DifferentShows> response) {

               DifferentShows differentShows = response.body();
               show_AiringToday.clear();
               show_AiringToday.addAll(differentShows.getResults());
               bundle1.putSerializable("showsList",show_AiringToday);
               bundle1.putString("code","show");
               fragment_airingToday.setArguments(bundle1);

               fragment_airingToday.setUpListener(new LargeList_Fragment.OnClickListener() {
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
               transaction.replace(R.id.container_airingTodayShow,fragment_airingToday).commit();

           }

           @Override
           public void onFailure(Call<DifferentShows> call, Throwable t) {
               Toast.makeText(getActivity(),"Could Not show airing show ",Toast.LENGTH_SHORT).show();

           }
       });


    }


    private void fetchOnTheAir() {

        Call<DifferentShows> call2= userApi.getTv_shows("on_the_air");
        call2.enqueue(new Callback<DifferentShows>() {
            @Override
            public void onResponse(Call<DifferentShows> call, Response<DifferentShows> response) {


                DifferentShows differentShows = response.body();
                show_OnTheAir.clear();
                show_OnTheAir.addAll(differentShows.getResults());

                bundle2.putSerializable("showsList",show_OnTheAir);
                bundle2.putString("code","show");
                fragment_onTheAir.setArguments(bundle2);

                fragment_onTheAir.setUpListener(new SmallList_Fragment.OnClickListener() {
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
                transaction.replace(R.id.container_onTheAirShow,fragment_onTheAir).commit();
            }

            @Override
            public void onFailure(Call<DifferentShows> call, Throwable t) {


                Toast.makeText(getActivity(),"Could Not show on the air ",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void fetchPopular() {
        Call<DifferentShows> call3= userApi.getTv_shows("popular");
        call3.enqueue(new Callback<DifferentShows>() {
            @Override
            public void onResponse(Call<DifferentShows> call, Response<DifferentShows> response) {


                DifferentShows differentShows = response.body();
                show_popular.clear();
                show_popular.addAll(differentShows.getResults());

                bundle3.putSerializable("showsList",show_popular);
                bundle3.putString("code","show");
                fragment_popular.setArguments(bundle3);

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
                transaction.replace(R.id.container_popularShow,fragment_popular).commit();
            }

            @Override
            public void onFailure(Call<DifferentShows> call, Throwable t) {


                Toast.makeText(getActivity(),"Could Not show popular ",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void fetchTopRated() {

        Call<DifferentShows> call4= userApi.getTv_shows("top_rated");
        call4.enqueue(new Callback<DifferentShows>() {
            @Override
            public void onResponse(Call<DifferentShows> call, Response<DifferentShows> response) {


                DifferentShows differentShows = response.body();
                show_topRated.clear();
                show_topRated.addAll(differentShows.getResults());

                bundle4.putSerializable("showsList",show_topRated);
                bundle4.putString("code","show");
                fragment_topRated.setArguments(bundle4);

                fragment_topRated.setUpListener(new SmallList_Fragment.OnClickListener() {
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
                transaction.replace(R.id.container_topRatedShow,fragment_topRated).commit();
            }

            @Override
            public void onFailure(Call<DifferentShows> call, Throwable t) {


                Toast.makeText(getActivity(),"Could Not top Rated ",Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void onClickItem(int position, Object object) {
        Show show= (Show)object;

        Bundle bundle =new Bundle();
        bundle.putString("poster",show.getPoster_path());
        bundle.putInt("id",show.getId());
        bundle.putString("code","show");
        bundle.putString("overView",show.getOverview());

        Fragment_Details fragment=new Fragment_Details();
        fragment.setArguments(bundle);

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main,fragment).commit();
    }


    private void OnHeartClick(int position, Object object) {
        userDAO.insertShow((Show)object);
    }

}
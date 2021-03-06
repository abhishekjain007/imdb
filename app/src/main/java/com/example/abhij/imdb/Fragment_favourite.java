package com.example.abhij.imdb;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_Large;
import com.example.abhij.imdb.Database.MovieDatabase;
import com.example.abhij.imdb.Database.UserDAO;
import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.ShowsClasses.Show;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_favourite extends Fragment {


    MovieDatabase database;
    UserDAO userDao;

    ArrayList<Show> showArrayList;
    ArrayList<Movie> movieArrayList;


    RecyclerView recyclerView_list;
    RecyclerView recyclerView_list1;

    UserRecyclerAdapter_Large adapter ;
    UserRecyclerAdapter_Large adapter1 ;


    String code;

    public Fragment_favourite() {
        // Required empty public constructor


        showArrayList = new ArrayList<>();
        movieArrayList = new ArrayList<>();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=  inflater.inflate(R.layout.fragment_fragment_favourite, container, false);

        database = Room.databaseBuilder(getActivity().getApplicationContext(),MovieDatabase.class,"movieDatabase")
                .allowMainThreadQueries()
                .build();
        userDao = database.getuserDAO();

        movieArrayList= (ArrayList<Movie>) userDao.getAllMovies();
        showArrayList = (ArrayList<Show>) userDao.getAllShowes();


        adapter= new UserRecyclerAdapter_Large("showFav", showArrayList, getActivity(), new UserRecyclerAdapter_Large.OnItemClicked() {
            @Override
            public void onClick(int position, Object object) {
                onClickItem(position,object,"show");
            }
        }, new UserRecyclerAdapter_Large.OnHeartClicked() {
            @Override
            public void onHeartClick(int position, Object object) {

                userDao.deleteShow((Show)object);
                showArrayList.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        adapter1 =new UserRecyclerAdapter_Large(getActivity(), movieArrayList, "movieFav", new UserRecyclerAdapter_Large.OnItemClicked() {
            @Override
            public void onClick(int position, Object object) {
                onClickItem(position,object,"movie");
            }
        }, new UserRecyclerAdapter_Large.OnHeartClicked() {
            @Override
            public void onHeartClick(int position, Object object) {
                userDao.deleteMovie((Movie)object);
                movieArrayList.remove(position);
                adapter1.notifyItemRemoved(position);
            }
        });

        recyclerView_list = view.findViewById(R.id.recycler_favouriteShows);
        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView_list.setAdapter(adapter);

        recyclerView_list1 = view.findViewById(R.id.recycler_favouriteMovies);
        recyclerView_list1.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView_list1.setAdapter(adapter1);



        return view;
    }
    public void onClickItem(int position, Object object,String code) {

        Bundle bundle =new Bundle();

        if(code.equals("movie")){
            Movie movie = (Movie) object;
            bundle.putString("poster", movie.getPoster_path());
            bundle.putInt("id", movie.getId());
            bundle.putString("overView", movie.getOverview());
        }else if(code.equals("show"))
        {
            Show show= (Show)object;
            bundle.putString("poster",show.getPoster_path());
            bundle.putInt("id",show.getId());
            bundle.putString("overView",show.getOverview());
        }

        bundle.putString("code",code);
        Fragment_Details fragment=new Fragment_Details();
        fragment.setArguments(bundle);

        FragmentManager manager =getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container_main,fragment).commit();
    }
//C:\Users\abhij\.gradle\caches\transforms-1\files-1.1\appcompat-v7-25.3.0.aar\5ebca5e97f0c300ed8ecabeec46f90c8\res\values-v21\values-v21.xml
}

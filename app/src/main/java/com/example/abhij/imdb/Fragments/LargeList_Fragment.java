package com.example.abhij.imdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.Trailer;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_Large;
import com.example.abhij.imdb.ShowsClasses.Show;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LargeList_Fragment extends Fragment {

    public interface OnClickListener{

        void onClick(int position, Object object);
    }
    public interface OnHeartClicked{
        void onHeartClick(int position, Object object);
    }

    OnHeartClicked heartListener;


    OnClickListener listener;


    RecyclerView recyclerView_list;
    UserRecyclerAdapter_Large adapter ;
    ArrayList<Movie> movieArrayList;
    ArrayList<Trailer> trailerArrayList;
    ArrayList<Show> showArrayList;
    String code;

    public LargeList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =  inflater.inflate(R.layout.fragment_broad_list_, container, false);

        Bundle bundle = getArguments();

        code= bundle.getString("code");

        if(code.equals("movie"))
        {
            movieArrayList= (ArrayList<Movie>) bundle.getSerializable("moviesList");

            adapter = new UserRecyclerAdapter_Large(getActivity(), movieArrayList,"movie", new UserRecyclerAdapter_Large.OnItemClicked() {
                @Override
                public void onClick(int position,Object object) {

                    listener.onClick(position,object);
                }
            }, new UserRecyclerAdapter_Large.OnHeartClicked() {
                @Override
                public void onHeartClick(int position, Object object) {
                    heartListener.onHeartClick(position,object);
                }
            });

        }

        if(code.equals("trailer"))
        {
            trailerArrayList= (ArrayList<Trailer>) bundle.getSerializable("trailerList");

            adapter = new UserRecyclerAdapter_Large( trailerArrayList,getActivity(),"trailer", new UserRecyclerAdapter_Large.OnItemClicked() {
                @Override
                public void onClick(int position,Object object) {

                    listener.onClick(position,object);
                }
            });

        }
        if(code.equals("show"))
        {
            showArrayList =(ArrayList<Show>) bundle.getSerializable("showsList");
            adapter = new UserRecyclerAdapter_Large("show",showArrayList,getActivity(),new UserRecyclerAdapter_Large.OnItemClicked(){
                @Override
                public void onClick(int position,Object object) {

                    listener.onClick(position,object);
                }
            }, new UserRecyclerAdapter_Large.OnHeartClicked() {
                @Override
                public void onHeartClick(int position, Object object) {
                    heartListener.onHeartClick(position,object);
                }
            });
        }

        recyclerView_list = view.findViewById(R.id.recyclerList_In_Large);



        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView_list.setAdapter(adapter);

        return view;
    }

//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        listener = (OnClickListener)context;
//    }


    public void setUpListener(LargeList_Fragment.OnClickListener mlistener){
        this.listener = mlistener;
    }

    public void setUpListener(LargeList_Fragment.OnClickListener mlistener,LargeList_Fragment.OnHeartClicked heartListener )
    {
        this.listener=mlistener;
        this.heartListener = heartListener;
    }
}

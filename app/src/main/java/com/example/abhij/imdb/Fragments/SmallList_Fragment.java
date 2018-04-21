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

import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_Large;
import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_Small;
import com.example.abhij.imdb.ShowsClasses.Show;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SmallList_Fragment extends Fragment {

    public interface OnClickListener{

        public void onClick(int position,Object object);
    }

    public interface OnHeartClicked{
        public void onHeartClick(int position,Object object);
    }

    SmallList_Fragment.OnClickListener listener;
    OnHeartClicked heartListener;

    ArrayList<Movie> movieArrayList;
    ArrayList<Show> showArrayList;

    RecyclerView recyclerView_list;
    UserRecyclerAdapter_Small adapter ;
    String code;

    public SmallList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_small_list_, container, false);


        Bundle bundle = getArguments();
        code=bundle.getString("code");

        if(code.equals("movie")) {
            movieArrayList = (ArrayList<Movie>) bundle.getSerializable("moviesList");

            adapter = new UserRecyclerAdapter_Small(getActivity(), movieArrayList, "movie", new UserRecyclerAdapter_Small.OnItemClicked() {
                @Override
                public void OnClick(int position, Object object) {

                    listener.onClick(position, object);

                }
            }, new UserRecyclerAdapter_Small.OnHeartClicked() {
                @Override
                public void onHeartClick(int position, Object object) {
                    heartListener.onHeartClick(position,object);
                }
            });
        }
        if(code.equals("show"))
        {
            showArrayList =(ArrayList<Show>) bundle.getSerializable("showsList");
            adapter = new UserRecyclerAdapter_Small("show",showArrayList,getActivity(),new UserRecyclerAdapter_Small.OnItemClicked(){
                @Override
                public void OnClick(int position,Object object) {

                    listener.onClick(position,object);
                }
            }, new UserRecyclerAdapter_Small.OnHeartClicked() {
                @Override
                public void onHeartClick(int position, Object object) {
                    heartListener.onHeartClick(position,object);
                }
            });
        }


        recyclerView_list = (RecyclerView) view.findViewById(R.id.recyclerList_Movies_In_Small);

        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView_list.setAdapter(adapter);

        return view;
    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        listener = (SmallList_Fragment.OnClickListener)context;
//    }

    public void setUpListener(SmallList_Fragment.OnClickListener listener)
    {
        this.listener = listener;
    }

    public void setUpListener(SmallList_Fragment.OnClickListener listener,SmallList_Fragment.OnHeartClicked heartListener)
    {
        this.listener= listener;
        this.heartListener =heartListener;
    }
//    @Override
//    public void onAttachFragment(Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//
//        listener = (SmallList_Fragment.OnClickListener)childFragment;
//
//    }
}

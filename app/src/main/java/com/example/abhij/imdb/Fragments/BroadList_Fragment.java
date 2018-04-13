package com.example.abhij.imdb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhij.imdb.Movie;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.UserRecyclerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BroadList_Fragment extends Fragment {

    RecyclerView recyclerView_list;
    UserRecyclerAdapter adapter ;
    ArrayList<Movie> movies_list;

    public BroadList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_broad_list_, container, false);


        recyclerView_list = (RecyclerView)view.findViewById(R.id.recyclerList_Movies);


        adapter = new UserRecyclerAdapter(getActivity(), movies_list, new UserRecyclerAdapter.OnItemClicked() {
            @Override
            public void onClick(int position) {

            }
        });

        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView_list.setAdapter(adapter);

        return view;
    }

}

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

import com.example.abhij.imdb.Movie;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.UserRecyclerAdapter_Large;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LargeList_Fragment extends Fragment {

    public interface OnClickListener{

        public void onClick(int position);
    }

    OnClickListener listener;

    RecyclerView recyclerView_list;
    UserRecyclerAdapter_Large adapter ;
    ArrayList<Movie> movies_list;

    public LargeList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =  inflater.inflate(R.layout.fragment_broad_list_, container, false);



        Bundle bundle = getArguments();
        movies_list= (ArrayList<Movie>) bundle.getSerializable("movies");

        recyclerView_list = (RecyclerView)view.findViewById(R.id.recyclerList_Movies_In_Large);


        adapter = new UserRecyclerAdapter_Large(getActivity(), movies_list, new UserRecyclerAdapter_Large.OnItemClicked() {
            @Override
            public void onClick(int position) {

                listener.onClick(position);
            }
        });

        recyclerView_list.setItemAnimator(new DefaultItemAnimator());
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView_list.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (OnClickListener)context;
    }
}

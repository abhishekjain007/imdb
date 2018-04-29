package com.example.abhij.imdb.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhij.imdb.Adapters.UserRecyclerAdapter_circle;
import com.example.abhij.imdb.MovieClasses.Cast;
import com.example.abhij.imdb.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleList_Fragment extends Fragment {

    public interface OnClickListener{
        void OnClick(int position);
    }

     CircleList_Fragment.OnClickListener listener;

    ArrayList<Cast> castArrayList;
    UserRecyclerAdapter_circle adapter ;
    RecyclerView recyclerView_list;

    public CircleList_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_circle_list_, container, false);

        Bundle bundle = getArguments();

        if(bundle.getString("code").equals("cast")) {
            castArrayList = (ArrayList<Cast>) bundle.getSerializable("cast");
            adapter = new UserRecyclerAdapter_circle(getContext(), castArrayList, "cast", new UserRecyclerAdapter_circle.OnClickListener() {
                @Override
                public void OnClick(int postion) {

                    listener.OnClick(postion);
                }
            });
        }

        recyclerView_list = view.findViewById(R.id.recyclerList_Circle);
        recyclerView_list.setAdapter(adapter);
        recyclerView_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        recyclerView_list.addItemDecoration(new DividerItemDecoration());
        recyclerView_list.setItemAnimator(new DefaultItemAnimator());


     return  view;
    }

  public void setUpListener(CircleList_Fragment.OnClickListener listener){
        this.listener = listener;

    }
}

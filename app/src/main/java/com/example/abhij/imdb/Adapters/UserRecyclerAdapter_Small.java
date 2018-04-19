package com.example.abhij.imdb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.Trailer;
import com.example.abhij.imdb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abhij on 13-04-2018.
 */

public class UserRecyclerAdapter_Small extends RecyclerView.Adapter<UserRecyclerAdapter_Small.UserHolder> {

    public interface OnItemClicked{

        public void OnClick(int position,Object Object);
    }

    ArrayList<Movie> movieArrayList ;
//    ArrayList<Trailer> TrailerArrayList;

    Context context;
    OnItemClicked listener;
    String code;

    public UserRecyclerAdapter_Small(Context context, ArrayList<Movie> movieArrayList,String code, OnItemClicked listener) {
        this.context = context;
        this.movieArrayList = movieArrayList;
        this.listener = listener;
        this.code= code;
    }
//
//    public UserRecyclerAdapter_Small( ArrayList<Movie> movies,Context context,String code, OnItemClicked listener) {
//        this.context = context;
//        this.movieArrayList = movieArrayList;
//        this.listener = listener;
//        this.code = code;
//    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_small,parent,false);
        UserHolder holder = new UserHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, int position) {


        if (code.equals("movie")) {
            String url = "http://image.tmdb.org/t/p/w342/" + movieArrayList.get(position).getPoster_path();
            Picasso.get().load(url).into(holder.poster);
            holder.title.setText(movieArrayList.get(position).getTitle());
            holder.occupied = true;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(holder.getAdapterPosition(), movieArrayList.get(holder.getAdapterPosition()));
                }
            });
        }

//        if (code.equals("movie")) {
//            String url = "http://image.tmdb.org/t/p/w342/" + movies.get(position).getPoster_path();
//            Picasso.get().load(url).into(holder.poster);
//            holder.title.setText(movies.get(position).getTitle());
//            holder.occupied = true;
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.OnClick(holder.getAdapterPosition(), movies.get(holder.getAdapterPosition()));
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        if(code.equals("movie"))
        return movieArrayList.size();

//        if(code.equals(""))

        return -1;
    }

    class UserHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;
        View itemView;
        Boolean occupied;

        public UserHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            poster = itemView.findViewById(R.id.poster_SmallItem);
            title= itemView.findViewById(R.id.title_SmallItem);
        }
    }
}




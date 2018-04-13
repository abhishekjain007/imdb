package com.example.abhij.imdb;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by abhij on 25-03-2018.
 */

public class UserRecyclerAdapter_Large extends RecyclerView.Adapter<UserRecyclerAdapter_Large.UserHolder> {

    public interface OnItemClicked{
        public void onClick(int position);
    }
    ArrayList<Movie> movies ;
    Context context;
    OnItemClicked listener;

    public UserRecyclerAdapter_Large(Context context, ArrayList<Movie> movies, OnItemClicked listener) {
        this.movies = movies;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.movie_item_large,parent,false);
        UserHolder holder = new UserHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, final int position) {


            String url = "http://image.tmdb.org/t/p/w780/" + movies.get(position).getBackdrop_path();
            Picasso.get().load(url).into(holder.poster);
            holder.title.setText(movies.get(position).getTitle());
            holder.occupied=true;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(holder.getAdapterPosition());
                }
            });
        }



    @Override
    public int getItemCount() {
        return movies.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{


        ImageView poster;
        TextView title;
        View itemView;
        Boolean occupied;
        UserHolder(View itemView)
        {
            super(itemView);
            this.itemView=itemView;
            poster = itemView.findViewById(R.id.poster_LargeMovieItem);
            title = itemView.findViewById(R.id.title_LargeMovieItem);

        }
    }

}

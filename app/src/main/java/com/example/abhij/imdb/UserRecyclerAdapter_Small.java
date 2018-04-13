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
 * Created by abhij on 13-04-2018.
 */

public class UserRecyclerAdapter_Small extends RecyclerView.Adapter<UserRecyclerAdapter_Small.UserHolder> {

    public interface OnItemClicked{

        public void OnClick(int position);
    }

    Context context;
    ArrayList<Movie> movies;
    OnItemClicked listener;

    public UserRecyclerAdapter_Small(Context context, ArrayList<Movie> movies, OnItemClicked listener) {
        this.context = context;
        this.movies = movies;
        this.listener = listener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.movie_item_small,parent,false);
        UserHolder holder = new UserHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, int position) {



            String url = "http://image.tmdb.org/t/p/w342 /" + movies.get(position).getPoster_path();
            Picasso.get().load(url).into(holder.poster);
            holder.title.setText(movies.get(position).getTitle());
            holder.occupied=true;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(holder.getAdapterPosition());
                }
            });
        }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title;
        View itemView;
        Boolean occupied;

        public UserHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            poster = itemView.findViewById(R.id.poster_SmallMovieItem);
            title= itemView.findViewById(R.id.title_SmallMovieItem);
        }
    }
}




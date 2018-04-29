package com.example.abhij.imdb.Adapters;


import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhij.imdb.MovieClasses.Movie;
import com.example.abhij.imdb.MovieClasses.Trailer;
import com.example.abhij.imdb.MovieDetail;
import com.example.abhij.imdb.R;
import com.example.abhij.imdb.ShowsClasses.Show;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;
import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by abhij on 25-03-2018.
 */

public class UserRecyclerAdapter_Large extends RecyclerView.Adapter<UserRecyclerAdapter_Large.UserHolder> {

    public interface OnItemClicked{
        void onClick(int position, Object object);
    }

    public interface OnHeartClicked{
        void onHeartClick(int position, Object object);
    }

    ArrayList<Movie> movieArrayList ;
    ArrayList<Trailer> trailerArrayList;
    ArrayList<Show> showArrayList;

    Context context;
    OnItemClicked listener;
    OnHeartClicked heartListener;
    String code;



    public UserRecyclerAdapter_Large(Context context, ArrayList<Movie> movieArrayList,String code, OnItemClicked listener,OnHeartClicked heartListener) {
        this.movieArrayList = movieArrayList;
        this.context = context;
        this.listener = listener;
        this.code=code;
        this.heartListener= heartListener;
    }

    public UserRecyclerAdapter_Large( ArrayList<Trailer> trailerArrayList,Context context,String code, OnItemClicked listener) {
        this.trailerArrayList = trailerArrayList;
        this.context = context;
        this.listener = listener;
        this.code=code;

    }

    public UserRecyclerAdapter_Large(String code,ArrayList<Show> showArrayList,Context context,OnItemClicked listener,OnHeartClicked heartListener)
    {
        this.showArrayList = showArrayList;
        this.context = context;
        this.listener= listener;
        this.code= code;
        this.heartListener = heartListener;
    }



    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_large,parent,false);
        UserHolder holder = new UserHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UserHolder holder, final int position) {



        if(code.equals("movie")||code.equals("movieFav")) {
            String url = "http://image.tmdb.org/t/p/w780/" + movieArrayList.get(position).getBackdrop_path();
            Picasso.get().load(url).into(holder.poster);
            holder.title.setText(movieArrayList.get(position).getTitle());
            holder.occupied = true;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(holder.getAdapterPosition(), movieArrayList.get(holder.getAdapterPosition()));
                }
            });

            if(code.equals("movieFav"))
            {
                holder.heart.setImageResource(R.drawable.heart_full);
            }
            holder.heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    heartListener.onHeartClick(holder.getAdapterPosition(), movieArrayList.get(holder.getAdapterPosition()));
                }
            });
        }



        if(code.equals("trailer")) {
                String url = "https://img.youtube.com/vi/"+trailerArrayList.get(position).getKey()+"/maxresdefault.jpg" ;
//                holder.poster.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,150));
                Picasso.get().load(url).into(holder.poster);
//                holder.itemView.findViewById(R.id.cardItem_large).setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//                holder.itemView.findViewById(R.id.cardItem_large).setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                holder.title.setVisibility(View.INVISIBLE);
//                holder.title.setText(TrailerArrayList.get(position).getName());
                holder.occupied = true;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick(holder.getAdapterPosition(), trailerArrayList.get(holder.getAdapterPosition()));
                    }
                });
            }

            if(code.equals("show")||code.equals("showFav"))
            {
                String url = "http://image.tmdb.org/t/p/w780/" + showArrayList.get(position).getBackdrop_path();
                Picasso.get().load(url).into(holder.poster);
                holder.title.setText(showArrayList.get(position).getOriginal_name());
                holder.occupied = true;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onClick(holder.getAdapterPosition(), showArrayList.get(holder.getAdapterPosition()));
                    }
                });


                if(code.equals("showFav"))
                {
                    holder.heart.setImageResource(R.drawable.heart_full);
                }
                holder.heart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        heartListener.onHeartClick(holder.getAdapterPosition(), showArrayList.get(holder.getAdapterPosition()));
                    }
                });
            }



    }



    @Override
    public int getItemCount() {

     if(code.equals("movie")||code.equals("movieFav"))
         return movieArrayList.size();

     if (code.equals("trailer")) {
         Log.d("trail",trailerArrayList.size()+"");
         return trailerArrayList.size();
     }
        if(code.equals("show")||code.equals("showFav"))
            return showArrayList.size();

        return -1;
    }

    class UserHolder extends RecyclerView.ViewHolder{


        ImageView heart;
        ImageView poster;
        TextView title;
        View itemView;
        Boolean occupied;
        UserHolder(View itemView)
        {
            super(itemView);
            this.itemView=itemView;
            poster = itemView.findViewById(R.id.poster_LargeItem);
            title = itemView.findViewById(R.id.title_LargeItem);
            heart= itemView.findViewById(R.id.heart_largeItem);
        }
    }

}

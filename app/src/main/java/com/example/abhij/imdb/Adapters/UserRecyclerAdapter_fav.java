package com.example.abhij.imdb.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by abhij on 21-04-2018.
 */

public class UserRecyclerAdapter_fav extends RecyclerView.Adapter<UserRecyclerAdapter_fav.UserHolder> {


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class UserHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView imageView;

        public UserHolder(View itemView) {
            super(itemView);
        }
    }
}

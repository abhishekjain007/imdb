package com.example.abhij.imdb.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhij.imdb.MovieClasses.Cast;
import com.example.abhij.imdb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abhij on 16-04-2018.
 */

public class UserRecyclerAdapter_circle extends RecyclerView.Adapter<UserRecyclerAdapter_circle.UserHolder> {

   public interface OnClickListener{
       void OnClick(int postion);
   }


    ArrayList<Cast> castArrayList;



    Context context;
    private OnClickListener listener;
    String code;

    public UserRecyclerAdapter_circle(Context context, ArrayList<Cast> castArrayList,String code, OnClickListener listener) {
        this.context = context;
        this.castArrayList = castArrayList;
        this.listener = listener;
        this.code = code;

    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView =  inflater.inflate(R.layout.item_circular,parent,false);
        UserHolder holder = new UserHolder(itemView);
        return holder;

    }

    @Override
    public void onBindViewHolder(final UserHolder holder, int position) {

        if(code.equals("cast")) {
            String url = "http://image.tmdb.org/t/p/w342/" + castArrayList.get(position).getProfile_path();
            Picasso.get().load(url).into(holder.imageView);

            holder.title.setText(castArrayList.get(position).getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnClick(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        if(code.equals("cast"))
        return castArrayList.size();

        return -1;
    }



     class UserHolder extends RecyclerView.ViewHolder{
         CircleImageView imageView;
         TextView title;
         View itemView;

         UserHolder(View itemView)
         {
             super(itemView);

             this.itemView = itemView;
             imageView = itemView.findViewById(R.id.image_circle);
             title= itemView.findViewById(R.id.title_cirle);
         }
    }
}

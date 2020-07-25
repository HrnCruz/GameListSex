package com.example.gamelistsex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gamelistsex.R;
import com.example.gamelistsex.model.Game;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemsViewHolder> {
    private Context mContext;
    private ArrayList<Game> lst_games;

    public RecyclerViewAdapter(Context mContext, ArrayList<Game> lst_games) {
        this.mContext = mContext;
        this.lst_games = lst_games;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.game_row_item, parent, false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
        Game currenItem = lst_games.get(position);

        String released = currenItem.getReleased();
        String name = currenItem.getName();
        String slug = currenItem.getSlug();
        String rating = currenItem.getRating();
        String urlImage = currenItem.getBackground_image();

        holder.tv_name.setText(name);
        holder.tv_rating.setText(rating);
        holder.tv_slug.setText(slug);
        holder.tv_released.setText(released);

        Picasso.get().load(urlImage).into(holder.img_thumbnall);

    }

    @Override
    public int getItemCount() {
        return lst_games.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public TextView tv_released;
        public TextView tv_slug;
        public TextView tv_rating;
        public ImageView img_thumbnall;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
            tv_released =itemView.findViewById(R.id.released);
            tv_slug = itemView.findViewById(R.id.slug);
            tv_rating = itemView.findViewById(R.id.rating);
            img_thumbnall =itemView.findViewById(R.id.imgthumbnail);

        }
    }

}

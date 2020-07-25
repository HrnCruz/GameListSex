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

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContexto;
    private List<Game> mData;
    RequestOptions options;

    public RecyclerViewAdapter(Context mContexto, List<Game> mData) {
        this.mContexto = mContexto;
        this.mData = mData;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.loading_shape)
                .error(R.drawable.loading_shape);
    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater =LayoutInflater.from(mContexto);
        view = inflater.inflate(R.layout.game_row_item,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_released.setText(mData.get(position).getReleased());
        holder.tv_slug.setText(mData.get(position).getSlug());


        Glide.with(mContexto).load(mData.get(position).getBackground_image()).apply(options).into(holder.img_thumbnall);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_released;
        TextView tv_slug;
        TextView tv_rating;
        ImageView img_thumbnall;


        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_released =itemView.findViewById(R.id.released);
            tv_slug = itemView.findViewById(R.id.slug);
            tv_rating = itemView.findViewById(R.id.rating);
            img_thumbnall =itemView.findViewById(R.id.imgthumbnail);
        }
    }
}

package com.example.tmdbtest.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tmdbtest.R;
import com.example.tmdbtest.model.response.MovieModel;
import com.example.tmdbtest.ui.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    Context context;
    List<MovieModel> movieModelList;

    public MovieAdapter(Context context, List<MovieModel> movieModelList) {
        this.context = context;
        this.movieModelList = movieModelList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.tvName.setText(movieModelList.get(position).getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/original/" + movieModelList.get(position).getPosterPath())
                .into(holder.ivPhoto);
        holder.itemView.setOnClickListener(view -> {
            Intent i = new Intent(context, DetailActivity.class);
            i.putExtra("movieId", movieModelList.get(position).getId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return movieModelList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName;

        public MovieViewHolder(@NonNull View v) {
            super(v);
            ivPhoto = v.findViewById(R.id.ivPhoto);
            tvName = v.findViewById(R.id.tvName);
        }
    }
}

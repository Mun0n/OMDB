package com.mun0n.omdb.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mun0n.omdb.R;
import com.mun0n.omdb.presentation.model.MovieModel;

import java.util.Collections;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    
    private List<MovieModel> moviesCollection;
    private final LayoutInflater layoutInflater;
    
    public MovieAdapter(Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        moviesCollection = Collections.emptyList();
    }
    
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(final ViewGroup parent,
                                                           final int viewType) {
        return new MovieViewHolder(layoutInflater.inflate(R.layout.row_movie, parent, false));
    }
    
    @Override
    public void onBindViewHolder(final MovieAdapter.MovieViewHolder holder, final int position) {
        final MovieModel movieModel = moviesCollection.get(position);
        holder.tvName.setText(movieModel.getName());
        
    }
    
    @Override
    public int getItemCount() {
        return moviesCollection.size();
    }
    
    public class MovieViewHolder extends RecyclerView.ViewHolder {
        
        private ImageView ivCover;
        private TextView tvName;
        
        public MovieViewHolder(final View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}

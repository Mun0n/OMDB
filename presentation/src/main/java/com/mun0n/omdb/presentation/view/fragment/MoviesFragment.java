package com.mun0n.omdb.presentation.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mun0n.omdb.R;
import com.mun0n.omdb.presentation.presenter.MoviesPresenter;
import com.mun0n.omdb.presentation.view.adapter.MovieAdapter;

public class MoviesFragment extends BaseFragment {
    
    private RecyclerView rvMovies;
    private MoviesPresenter moviesPresenter;
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        rvMovies = view.findViewById(R.id.rvMovies);
        setupRecyclerView();
        return view;
    }
    
    private void setupRecyclerView() {
        
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setAdapter(new MovieAdapter(getActivity()));
    }
}

package com.mun0n.omdb.presentation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mun0n.omdb.R;
import com.mun0n.omdb.presentation.model.MovieModel;
import com.mun0n.omdb.presentation.presenter.MoviesPresenter;
import com.mun0n.omdb.presentation.view.MoviesView;
import com.mun0n.omdb.presentation.view.adapter.MovieAdapter;

import java.util.Collection;

public class MoviesFragment extends BaseFragment implements MoviesView {
    
    public interface MovieListListener {
        void onMovieClicked(final MovieModel movieModel);
    }
    
    private RecyclerView rvMovies;
    private MoviesPresenter moviesPresenter;
    private RelativeLayout rlProgress;
    private RelativeLayout rlRetry;
    
    private MovieListListener movieListListener;
    private MovieAdapter movieAdapter;
    
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movies, container, false);
        
        moviesPresenter = new MoviesPresenter();
        movieAdapter = new MovieAdapter(getActivity());
        
        rvMovies = view.findViewById(R.id.rvMovies);
        rlProgress = view.findViewById(R.id.rl_progress);
        rlRetry = view.findViewById(R.id.rl_retry);
        setupRecyclerView();
        return view;
    }
    
    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesPresenter.setView(this);
        if (savedInstanceState == null) {
            loadMoviesList();
        }
    }
    
    private void loadMoviesList() {
        moviesPresenter.initialize();
    }
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MovieListListener) {
            movieListListener = (MovieListListener) activity;
        }
    }
    
    private void setupRecyclerView() {
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setAdapter(movieAdapter);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        moviesPresenter.onResume();
    }
    
    @Override
    public void onPause() {
        super.onPause();
        moviesPresenter.onPause();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        moviesPresenter.onDestroy();
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        moviesPresenter = null;
    }
    
    @Override
    public void showLoading() {
        rlProgress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }
    
    @Override
    public void hideLoading() {
        rlProgress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }
    
    @Override
    public void showRetry() {
        rlRetry.setVisibility(View.VISIBLE);
    }
    
    @Override
    public void hideRetry() {
        rlRetry.setVisibility(View.GONE);
    }
    
    @Override
    public void showError(final String message) {
        showError(message);
    }
    
    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
    
    @Override
    public void renderMoviesList(final Collection<MovieModel> movieModelCollection) {
        if (movieModelCollection != null) {
            movieAdapter.setMoviesCollection(movieModelCollection);
        }
    }
    
    @Override
    public void viewMovie(final MovieModel movieModel) {
        if (movieListListener != null) {
            movieListListener.onMovieClicked(movieModel);
        }
    }
}

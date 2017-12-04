package com.mun0n.omdb.presentation.presenter;

import android.support.annotation.NonNull;

import com.mun0n.domain.interactor.GetMovieList;
import com.mun0n.omdb.presentation.mapper.MovieModelDataMapper;
import com.mun0n.omdb.presentation.model.MovieModel;
import com.mun0n.omdb.presentation.view.MoviesView;

public class MoviesPresenter implements Presenter {
    
    private MoviesView viewMovies;
    
    private final GetMovieList getMovieList;
    private final MovieModelDataMapper movieModelDataMapper;
    
    public MoviesPresenter(GetMovieList getMovieList, MovieModelDataMapper movieModelDataMapper) {
        this.getMovieList = getMovieList;
        this.movieModelDataMapper = movieModelDataMapper;
    }
    
    @Override
    public void onResume() {
    
    }
    
    @Override
    public void onPause() {
    
    }
    
    @Override
    public void onDestroy() {
        
        viewMovies = null;
    }
    
    public void initialize() {
        loadMovieList();
    }
    
    private void loadMovieList() {
        hideViewRetry();
        showViewLoading();
        //getMovieList();
    }
    
    private void showViewLoading() {
        viewMovies.showLoading();
    }
    
    private void hideViewLoading() {
        viewMovies.hideLoading();
    }
    
    private void showViewRetry() {
        viewMovies.showRetry();
    }
    
    private void hideViewRetry() {
        viewMovies.hideRetry();
    }
    
    private void showErrorMessage(String errorBunlde) {
    
    }
    
    public void setView(@NonNull final MoviesView view) {
        viewMovies = view;
    }
    
    public void onMovieClicked(MovieModel movieModel) {
        viewMovies.viewMovie(movieModel);
    }
}

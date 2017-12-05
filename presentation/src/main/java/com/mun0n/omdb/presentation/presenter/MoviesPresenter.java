package com.mun0n.omdb.presentation.presenter;

import android.support.annotation.NonNull;

import com.mun0n.domain.Movie;
import com.mun0n.domain.exception.DefaultErrorBundle;
import com.mun0n.domain.exception.ErrorBundle;
import com.mun0n.domain.interactor.DefaultObserver;
import com.mun0n.domain.interactor.GetMovieList;
import com.mun0n.omdb.presentation.ErrorMessageFactory;
import com.mun0n.omdb.presentation.mapper.MovieModelDataMapper;
import com.mun0n.omdb.presentation.model.MovieModel;
import com.mun0n.omdb.presentation.view.MoviesView;

import java.util.Collection;
import java.util.List;

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
        // Empty
    }
    
    @Override
    public void onPause() {
        // Empty
    }
    
    @Override
    public void onDestroy() {
        getMovieList.dispose();
        viewMovies = null;
    }
    
    public void initialize() {
        loadMovieList();
    }
    
    private void loadMovieList() {
        hideViewRetry();
        showViewLoading();
        getMovieList();
    }
    
    private void getMovieList() {
        getMovieList.execute(new MovieListObserver(), null);
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
    
    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewMovies.context(),
                errorBundle.getException());
        this.viewMovies.showError(errorMessage);
    }
    
    public void setView(@NonNull final MoviesView view) {
        viewMovies = view;
    }
    
    public void onMovieClicked(MovieModel movieModel) {
        viewMovies.viewMovie(movieModel);
    }
    
    private final class MovieListObserver extends DefaultObserver<List<Movie>> {
        
        @Override
        public void onNext(final List<Movie> movies) {
            MoviesPresenter.this.showMovieCollectionInView(movies);
        }
        
        @Override
        public void onError(final Throwable e) {
            MoviesPresenter.this.hideViewLoading();
            MoviesPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            MoviesPresenter.this.showViewRetry();
        }
        
        @Override
        public void onComplete() {
            MoviesPresenter.this.hideViewLoading();
        }
    }
    
    private void showMovieCollectionInView(final List<Movie> movies) {
        final Collection<MovieModel> movieModelsCollection = this.movieModelDataMapper.transform(
                movies);
        this.viewMovies.renderMoviesList(movieModelsCollection);
    }
}

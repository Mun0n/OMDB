package com.mun0n.omdb.presentation.view;

import com.mun0n.omdb.presentation.model.MovieModel;

import java.util.Collection;

public interface MoviesView extends LoadDataView {
    
    void renderMoviesList(Collection<MovieModel> movieModelCollection);
    
    void viewMovie(MovieModel movieModel);
}

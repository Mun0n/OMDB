package com.mun0n.omdb.presentation.mapper;

import com.mun0n.domain.Movie;
import com.mun0n.omdb.presentation.model.MovieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MovieModelDataMapper {
    
    public MovieModel transform(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final MovieModel movieModel = new MovieModel();
        movieModel.setName(movie.getName());
        movieModel.setCoverUrl(movie.getCoverUrl());
        return movieModel;
    }
    
    public Collection<MovieModel> transform(Collection<Movie> moviesCollection) {
        Collection<MovieModel> moviesModelCollection;
        
        if (moviesCollection != null && !moviesCollection.isEmpty()) {
            moviesModelCollection = new ArrayList<>();
            for (Movie movie : moviesCollection) {
                moviesModelCollection.add(transform(movie));
            }
        } else {
            moviesModelCollection = Collections.emptyList();
        }
        
        return moviesModelCollection;
    }
}

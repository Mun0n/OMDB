package com.mun0n.domain.repository;

import com.mun0n.domain.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MovieRepository {
    
    Observable<List<Movie>> movies();
    
    Observable<Movie> movie(final int movieId);
}

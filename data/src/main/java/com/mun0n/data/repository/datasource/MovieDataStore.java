package com.mun0n.data.repository.datasource;

import com.mun0n.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDataStore {
    
    Observable<List<MovieEntity>> movieEntityList();
    
    Observable<MovieEntity> movieEntityDetails(final int movieId);
}

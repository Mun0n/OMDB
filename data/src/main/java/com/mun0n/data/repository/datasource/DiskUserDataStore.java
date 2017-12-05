package com.mun0n.data.repository.datasource;

import com.mun0n.data.cache.MovieCache;
import com.mun0n.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Observable;

class DiskUserDataStore implements MovieDataStore {
    
    private final MovieCache movieCache;
    
    public DiskUserDataStore(final MovieCache movieCache) {
        this.movieCache = movieCache;
    }
    
    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        throw new UnsupportedOperationException("Operation is not available!!!");
    }
    
    @Override
    public Observable<MovieEntity> movieEntityDetails(final int movieId) {
        return this.movieCache.get(movieId);
    }
}

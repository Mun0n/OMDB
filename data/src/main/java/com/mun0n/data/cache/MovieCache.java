package com.mun0n.data.cache;

import com.mun0n.data.entity.MovieEntity;

import io.reactivex.Observable;

public interface MovieCache {
    
    Observable<MovieEntity> get(final int movieId);
    
    void put(MovieEntity movieEntity);
    
    boolean isCached(final int movieId);
    
    boolean isExpired();
    
    void evictAll();
}

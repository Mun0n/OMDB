package com.mun0n.data.repository.datasource;

import com.mun0n.data.cache.MovieCache;
import com.mun0n.data.entity.MovieEntity;
import com.mun0n.data.net.RestApi;

import java.util.List;

import io.reactivex.Observable;

class CloudUserDataStore implements MovieDataStore {
    
    private RestApi restApi;
    private final MovieCache movieCache;
    
    public CloudUserDataStore(final RestApi restApi, final MovieCache movieCache) {
        this.restApi = restApi;
        this.movieCache = movieCache;
    }
    
    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        return this.restApi.movieEntityList();
    }
    
    @Override
    public Observable<MovieEntity> movieEntityDetails(final int movieId) {
        return this.restApi.movieEntityById(movieId).doOnNext(CloudUserDataStore.this.movieCache::put);
    }
}

package com.mun0n.data.repository;

import com.mun0n.data.entity.mapper.MovieEntityDataMapper;
import com.mun0n.data.repository.datasource.MovieDataStore;
import com.mun0n.data.repository.datasource.MovieDataStoreFactory;
import com.mun0n.domain.Movie;
import com.mun0n.domain.repository.MovieRepository;

import java.util.List;

import io.reactivex.Observable;

public class MovieDataRepository implements MovieRepository {
    
    private final MovieDataStoreFactory movieDataStoreFactory;
    private final MovieEntityDataMapper movieEntityDataMapper;
    
    public MovieDataRepository() {
        movieEntityDataMapper = MovieEntityDataMapper.getInstance();
    }
    
    @Override
    public Observable<List<Movie>> movies() {
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.createCloudDataStore();
        return movieDataStore.movieEntityList().map(this.movieEntityDataMapper::transform);
    }
    
    @Override
    public Observable<Movie> movie(final int movieId) {
        final MovieDataStore movieDataStore = this.movieDataStoreFactory.create(movieId);
        return movieDataStore.movieEntityDetails(movieId).map(this.movieEntityDataMapper::transform);
    }
    
    public static MovieDataRepository getInstance() {
        return Singleton.INSTANCE;
    }
    
    private static class Singleton {
        private static final MovieDataRepository INSTANCE = new MovieDataRepository();
    }
}

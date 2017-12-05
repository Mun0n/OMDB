package com.mun0n.data.net;

import com.mun0n.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RestApi {
    
    String API_BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=399f8afa1464e4432f3cd86156d91ccb&language=en-US";
    
    Observable<List<MovieEntity>> movieEntityList();
    
    Observable<MovieEntity> movieEntityById(final int movieId);
}

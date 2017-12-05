package com.mun0n.data.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mun0n.data.entity.MovieEntity;
import com.mun0n.data.entity.mapper.MovieEntityJsonMapper;
import com.mun0n.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

public class RestApiImpl implements RestApi {
    
    private final Context context;
    private final MovieEntityJsonMapper movieEntityJsonMapper;
    
    public RestApiImpl(final Context context, final MovieEntityJsonMapper movieEntityJsonMapper) {
        if (context == null || movieEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.movieEntityJsonMapper = movieEntityJsonMapper;
    }
    
    @Override
    public Observable<List<MovieEntity>> movieEntityList() {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserEntities = getMovieEntitiesFromApi();
                    if (responseUserEntities != null) {
                        emitter.onNext(movieEntityJsonMapper.transformMovieEntityCollection(
                                responseUserEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }
    
    @Override
    public Observable<MovieEntity> movieEntityById(final int movieId) {
        return Observable.create(emitter -> {
            if (isThereInternetConnection()) {
                try {
                    String responseUserDetails = getMovieDetailsFromApi(movieId);
                    if (responseUserDetails != null) {
                        emitter.onNext(movieEntityJsonMapper.transformMovieEntity(
                                responseUserDetails));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new NetworkConnectionException());
                    }
                } catch (Exception e) {
                    emitter.onError(new NetworkConnectionException(e.getCause()));
                }
            } else {
                emitter.onError(new NetworkConnectionException());
            }
        });
    }
    
    private String getMovieEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_BASE_URL).requestSyncCall();
    }
    
    private String getMovieDetailsFromApi(final int movieId) throws MalformedURLException {
        return ApiConnection.createGET(API_BASE_URL).requestSyncCall();
    }
    
    private boolean isThereInternetConnection() {
        boolean isConnected;
        
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        
        return isConnected;
    }
}

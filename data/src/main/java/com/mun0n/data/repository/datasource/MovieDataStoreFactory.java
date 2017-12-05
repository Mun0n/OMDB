package com.mun0n.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mun0n.data.cache.MovieCache;
import com.mun0n.data.entity.mapper.MovieEntityJsonMapper;
import com.mun0n.data.net.RestApi;
import com.mun0n.data.net.RestApiImpl;

public class MovieDataStoreFactory {
    
    private final Context context;
    private final MovieCache movieCache;
    
    MovieDataStoreFactory(@NonNull Context context, @NonNull MovieCache movieCache) {
        this.context = context.getApplicationContext();
        this.movieCache = movieCache;
    }
    
    public MovieDataStore create(int movieId) {
        MovieDataStore userDataStore;
        
        if (!this.movieCache.isExpired() && this.movieCache.isCached(movieId)) {
            userDataStore = new DiskUserDataStore(this.movieCache);
        } else {
            userDataStore = createCloudDataStore();
        }
        
        return userDataStore;
    }
    
    public MovieDataStore createCloudDataStore() {
        final MovieEntityJsonMapper userEntityJsonMapper = new MovieEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);
        
        return new CloudUserDataStore(restApi, this.movieCache);
    }
    
}

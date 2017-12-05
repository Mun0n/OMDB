package com.mun0n.data.entity.mapper;

import com.mun0n.data.entity.MovieEntity;
import com.mun0n.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieEntityDataMapper {
    
    public static MovieEntityDataMapper getInstance() {
        return Singleton.INSTANCE;
    }
    
    private static class Singleton {
        private static final MovieEntityDataMapper INSTANCE = new MovieEntityDataMapper();
    }
    
    public Movie transform(MovieEntity movieEntity) {
        Movie movie = null;
        if (movieEntity != null) {
            movie = new Movie(movieEntity.getId());
            movie.setCoverUrl(movieEntity.getPosterPath());
            movie.setName(movieEntity.getTitle());
        }
        return movie;
    }
    
    public List<Movie> transform(Collection<MovieEntity> movieEntityCollection) {
        final List<Movie> moviesList = new ArrayList<>(20);
        for (MovieEntity movieEntity : movieEntityCollection) {
            final Movie user = transform(movieEntity);
            if (user != null) {
                moviesList.add(user);
            }
        }
        return moviesList;
    }
    
}

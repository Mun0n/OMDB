package com.mun0n.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mun0n.data.entity.MovieEntity;

import java.lang.reflect.Type;
import java.util.List;

public class MovieEntityJsonMapper {
    
    private final Gson gson;
    
    public MovieEntityJsonMapper() {
        this.gson = new Gson();
    }
    
    public MovieEntity transformMovieEntity(String movieJsonResponse) throws JsonSyntaxException {
        final Type movieEntityType = new TypeToken<MovieEntity>() {
        }.getType();
        return this.gson.fromJson(movieJsonResponse, movieEntityType);
    }
    
    public List<MovieEntity> transformMovieEntityCollection(
            String movieListJsonResponse) throws JsonSyntaxException {
        final Type listOfMovieEntityType = new TypeToken<List<MovieEntity>>() {
        }.getType();
        return this.gson.fromJson(movieListJsonResponse, listOfMovieEntityType);
    }
}

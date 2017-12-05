package com.mun0n.data.entity;

import com.google.gson.annotations.SerializedName;

public class MovieEntity {
    
    @SerializedName("id")
    private long id;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    
    public long getId() {
        return id;
    }
    
    public void setId(final long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getPosterPath() {
        return posterPath;
    }
    
    public void setPosterPath(final String posterPath) {
        this.posterPath = posterPath;
    }
}

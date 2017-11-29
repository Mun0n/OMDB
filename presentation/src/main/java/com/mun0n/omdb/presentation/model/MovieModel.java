package com.mun0n.omdb.presentation.model;

public class MovieModel {
    
    private String coverUrl;
    private String name;
    
    public String getCoverUrl() {
        return coverUrl;
    }
    
    public void setCoverUrl(final String coverUrl) {
        this.coverUrl = coverUrl;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}

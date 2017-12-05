package com.mun0n.domain;

public class Movie {
    
    private long id;
    private String name;
    private String coverUrl;
    
    public Movie(final long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getCoverUrl() {
        return coverUrl;
    }
    
    public void setCoverUrl(final String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

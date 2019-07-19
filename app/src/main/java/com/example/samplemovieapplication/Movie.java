package com.example.samplemovieapplication;

public class Movie {
    String name;
    String icon;
    String language;
    String genre;
    Double views,rating;
    public Movie(String name, String icon, String language, String genre, Double views, Double rating) {
        this.name = name;
        this.icon = icon;
        this.language = language;
        this.genre = genre;
        this.views = views;
        this.rating = rating;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Double getViews() {
        return views;
    }
    public void setViews(Double views) {
        this.views = views;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
}

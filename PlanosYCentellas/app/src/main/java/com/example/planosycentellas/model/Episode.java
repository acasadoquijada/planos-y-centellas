package com.example.planosycentellas.model;

import androidx.annotation.NonNull;

public class Episode {

    private String title;
    private String description;
    private String url;
    private String image;

    public Episode(){
        title = "";
        description = "";
        url = "";
        image = "";
    };

    public Episode(Episode episode){
        title = episode.getTitle();
        description = episode.getDescription();
        url = episode.getUrl();
        image = episode.getImage();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public boolean isEmpty(){

        boolean a = title.isEmpty();
        boolean b = description.isEmpty();
        boolean c = !(url.contains("https") || url.contains("http"));
        boolean d = !(image.contains("https") || image.contains("http"));

        return a && b && c && d;
    }

    public void clean(){
        title = "";
        description = "";
        url = "";
        image = "";
    }

    @NonNull
    @Override
    public String toString() {

        return "\nTITLE: " + title +  "\nURL: " + url + "\nIMAGE: " + image;
    }
}

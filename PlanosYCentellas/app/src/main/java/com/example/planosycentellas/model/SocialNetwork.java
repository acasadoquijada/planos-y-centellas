package com.example.planosycentellas.model;

public class SocialNetwork {

    private String name;
    private String url;

    public SocialNetwork(String name, String url){
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }
}

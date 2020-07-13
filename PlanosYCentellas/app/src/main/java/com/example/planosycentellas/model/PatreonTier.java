package com.example.planosycentellas.model;

public class PatreonTier {

    private String title;
    private String image;
    private String price;
    private String rewards;
    private String link;

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getRewards() {
        return rewards;
    }

    public String getLink() {
        return link;
    }

    public String getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.example.planosycentellas.model;

public class PatreonTier {

    private String title;
    private String image;
    private String price;
    private PatreonAwards awards;
    private String link;

    public PatreonTier(){
        title = "";
        image= "";
        price= "";
        awards = new PatreonAwards();
        link= "";
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public PatreonAwards getAwards() {
        return awards;
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

    public void setAwards(PatreonAwards awards) {
        this.awards = awards;
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

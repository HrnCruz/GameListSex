package com.example.gamelistsex.model;

public class Game {

    private String name;
    private String slug;
    private String released;
    private String rating;
    private String background_image;

    public Game() {

    }

    public Game(String name, String slug, String released, String rating, String background_image) {
        this.name = name;
        this.slug = slug;
        this.released = released;
        this.rating = rating;
        this.background_image = background_image;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getReleased() {
        return released;
    }

    public String getRating() {
        return rating;
    }

    public String getBackground_image() {
        return background_image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }
}





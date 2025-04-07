package com.group16.model;

public class Recipe {
    
    private String name;
    private String instructions;
    private String info;
    private String ingredients;
    private int protions;
    private String time;
    private boolean published = false;
    private double rating;

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getProtions() {
        return protions;
    }

    public void setProtions(int protions) {
        this.protions = protions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}

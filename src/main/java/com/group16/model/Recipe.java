package com.group16.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Recipe {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private String name;
    private String instructions;
    private String info;
    private String ingredients;
    private int portions;
    private String time;
    private boolean published = false;
    private Double rating;

    public Recipe() {}

    public Recipe(String name, String instructions, String info, String ingredients, int portions, String time, boolean published, Double rating) {
        this.name = name;
        this.instructions = instructions;
        this.info = info;
        this.ingredients = ingredients;
        this.portions = portions;
        this.time = LocalDateTime.now().format(formatter);
        this.published = published;
        this.rating = rating;
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

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
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

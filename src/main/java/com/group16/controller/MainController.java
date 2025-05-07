package com.group16.controller;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;
import com.group16.util.RecipeStyle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<Recipe> recipes;
    private List<Recipe> recipesByStyle;

    public MainController() {
        try {
            DatabaseService databaseService = DatabaseService.getInstance();
            recipes = databaseService.getAllRecipes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<Recipe> queryRecipes(String query, RecipeStyle style) {
        if (style != null) {
            filterByGenre(style);
            return getResults(recipesByStyle, query);
        }
        return getResults(recipes, query);
    }

    private List<Recipe> getResults(List<Recipe> from, String query) {
        List<Recipe> results = new ArrayList<>();
        from.stream()
                .filter(r -> r.getName().toLowerCase().contains(query.toLowerCase()) ||
                        r.getInfo().toLowerCase().contains(query.toLowerCase()))
                .forEach(results::add);
        return results;
    }

    private void filterByGenre(RecipeStyle style) {
        List<Recipe> results = new ArrayList<>();
        recipes.stream()
                .filter(r -> r.getGenre().equals(style))
                .forEach(results::add);
        recipesByStyle = results;
    }
}

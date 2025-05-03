package com.group16.controller;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<Recipe> recipes;

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

    public List<Recipe> queryRecipes(String query) {
        List<Recipe> results = new ArrayList<>();
        recipes.stream()
                .filter(r -> r.getName().toLowerCase().contains(query.toLowerCase()) ||
                        r.getInfo().toLowerCase().contains(query.toLowerCase())
                )
                .forEach(results::add);
        return results;
    }
}

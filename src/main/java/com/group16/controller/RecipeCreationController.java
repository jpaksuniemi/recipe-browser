package com.group16.controller;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;

public class RecipeCreationController {

    private final DatabaseService databaseService = DatabaseService.getInstance();
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;

    public RecipeCreationController() {}

    public int addRecipe (Recipe recipe){
        try{
            databaseService.createRecipe(recipe);
            return SUCCESS;
        } catch (Exception e){
            System.out.println("Error creating recipe" + e.getMessage());
            return ERROR;
        }
    }

}

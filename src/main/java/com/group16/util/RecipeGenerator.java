package com.group16.util;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;

public class RecipeGenerator {

    /**
     * Inserts three placeholder recipes into the database
     */
    public static void fillDatabase() {
        DatabaseService db = DatabaseService.getInstance();
        String[] names = {"Makaronilaatikko", "Jauhelihakastike", "Kalakeitto"};
        int portions = 4;
        String time = "60min";
        String filler = "lorem ipsum dolor sit amet";

        try {
            if (db.getAllRecipes().isEmpty()) {
                    for (String name : names) {
                        db.createRecipe(new Recipe(name, filler, filler, filler, portions, time, true, null));
                    }
            }
        } catch (Exception e) {
            System.out.println("Error while populating database: " + e.getMessage());
        }
    }
}

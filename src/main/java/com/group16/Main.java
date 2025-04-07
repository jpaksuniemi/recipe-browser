package com.group16;

import com.group16.service.DatabaseService;
import com.group16.util.RecipeGenerator;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseService db = new DatabaseService();

        try {
            db.initializeDatabase();
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
        RecipeGenerator.fillDatabase(db);
        System.out.println(db.getAllRecipes());
    }
}

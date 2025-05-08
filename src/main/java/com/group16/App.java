package com.group16;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;
import com.group16.tools.RecipeParser;
import com.group16.util.SceneSwitcher;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        setupDatabase();
        SceneSwitcher.initialize(stage);
        stage.show();
    }

    private static void setupDatabase() {
        try {
            DatabaseService instance = DatabaseService.getInstance();
            System.out.println(System.getProperty("java.class.path"));
            if (instance.getAllRecipes().isEmpty()) {
                InputStream resourceAsStream = App.class.getResourceAsStream("/placeholder-recipes.json");
                List<Recipe> recipes = new ArrayList<>();
                if (resourceAsStream == null) {
                    System.out.println("Resource not found: placeholder-recipes.json");
                } else {
                    recipes = RecipeParser.parseRecipes(resourceAsStream);
                }

                for (Recipe recipe : recipes) {
                    instance.createRecipe(recipe);
                }
            }
            if (!instance.authenticateUser("admin", "admin")) {
                instance.addUser("admin", "admin");
            }
        } catch (Exception e) {
            System.out.println("Error initializing example recipes and admin user: ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
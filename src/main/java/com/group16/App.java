package com.group16;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;
import com.group16.tools.RecipeParser;
import com.group16.util.ConstantValues;
import com.group16.util.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
            if (instance.getAllRecipes().isEmpty()) {
                List<Recipe> recipes = RecipeParser.parseRecipes(ConstantValues.RECIPE_FILEPATH);
                for (Recipe recipe : recipes) {
                    instance.createRecipe(recipe);
                }
            }
            if (!instance.authenticateUser("admin", "admin")) {
                instance.addUser("admin", "admin");
            }
        } catch (Exception e) {
            System.out.println("Error initializing example recipes and admin user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
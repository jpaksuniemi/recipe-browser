package com.group16;

import com.group16.model.Recipe;
import com.group16.service.DatabaseService;
import com.group16.tools.RecipeGenerator;
import com.group16.tools.RecipeParser;
import com.group16.util.ConstantValues;
import com.group16.util.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            if (DatabaseService.getInstance().getAllRecipes().isEmpty()) {
                List<Recipe> recipes = RecipeParser.parseRecipes(ConstantValues.RECIPE_FILEPATH);
                for (Recipe recipe : recipes) {
                    DatabaseService.getInstance().createRecipe(recipe);
                }
            }
        } catch (Exception e) {
            System.out.println("Error initializing example recipes: " + e.getMessage());
        }
        SceneSwitcher.initialize(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
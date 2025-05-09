package com.group16.service;

import com.group16.model.Recipe;
import com.group16.util.RecipeStyle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseService {

    private static final String DB_PATH = "./RecipeDatabase";
    private Connection connection;
    private static DatabaseService databaseService;

    private DatabaseService() throws SQLException {
        initializeDatabase();
        databaseService = this;
    }

    public static DatabaseService getInstance() {
        if (databaseService == null) {
            try {
                DatabaseService databaseService = new DatabaseService();
            } catch (Exception e) {
                System.out.println("Error initializing DatabaseService: " + e.getMessage());
            }
        }
        return databaseService;
    }

    public List<Recipe> getAllRecipes() throws SQLException {
        String selectAllSQL = "SELECT * FROM recipes";
        List<Recipe> recipes = new ArrayList<>();
        try (Statement selectAll = connection.createStatement()) {
            ResultSet recipesSet = selectAll.executeQuery(selectAllSQL);
            extractRecipes(recipesSet, recipes);
        }
        return recipes;
    }

    /**
     * Extracts Recipe objects from a ResultSet
     * @param recipesSet ResultSet that contains all columns from the recipes table
     * @param recipes List that will contain the extracted Recipes
     * @throws SQLException
     */
    private static void extractRecipes(ResultSet recipesSet, List<Recipe> recipes) throws SQLException {
        while (recipesSet.next()) {
            String name = recipesSet.getString("name");
            String instructions = recipesSet.getString("instructions");
            String info = recipesSet.getString("info");
            String ingredients = recipesSet.getString("ingredients");
            int portions = recipesSet.getInt("portions");
            String time = recipesSet.getString("time");
            RecipeStyle genre = RecipeStyle.fromInt(recipesSet.getInt("genre"));
            boolean published = recipesSet.getBoolean("is_published");

            Recipe recipe = new Recipe(name, instructions, info, ingredients, portions, time, published, genre);
            recipes.add(recipe);
        }
    }

    public void createRecipe(Recipe recipe) throws SQLException {
        String createRecipeSQL = "INSERT INTO recipes (name, instructions, info, ingredients, portions, time, genre, is_published) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement createRecipe = connection.prepareStatement(createRecipeSQL)) {
            createRecipe.setString(1, recipe.getName());
            createRecipe.setString(2, recipe.getInstructions());
            createRecipe.setString(3, recipe.getInfo());
            createRecipe.setString(4, recipe.getIngredients());
            createRecipe.setInt(5, recipe.getPortions());
            createRecipe.setString(6, recipe.getTime());
            createRecipe.setInt(7, recipe.getGenre().getValue());
            createRecipe.setBoolean(8, recipe.isPublished());
            createRecipe.executeUpdate();
        }

    }

    /**
     *
      * @param query Search recipes from the database using a string that is matched against the recipes name, info and ingredients
     */
    public List<Recipe> searchRecipe(String query) throws SQLException {
        String searchRecipeSQL = "SELECT * FROM recipes WHERE name = ? OR instructions = ? OR info = ?";
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement searchRecipe = connection.prepareStatement(searchRecipeSQL)) {
            searchRecipe.setString(1, query);
            searchRecipe.setString(2, query);
            searchRecipe.setString(3, query);
            ResultSet resultSet = searchRecipe.executeQuery();
            extractRecipes(resultSet, recipes);
        }
        return recipes;
    }

    public boolean addUser (String username, String password)throws SQLException{
        String addUser = "INSERT INTO users(username, password) VALUES (?,?)";

        if(isUsernameAvailable(username)){
            try (PreparedStatement add = connection.prepareStatement(addUser)){
                add.setString(1, username);
                add.setString(2, password);
                add.executeUpdate();
            }
            return true;
        }
        return false;
    }

    public boolean isUsernameAvailable(String username) throws SQLException {
        String searchUsername = "SELECT EXISTS(SELECT 1 FROM users WHERE username = ?)";

        try(PreparedStatement searchUser = connection.prepareStatement(searchUsername)){
            searchUser.setString(1, username);
            ResultSet resultSet = searchUser.executeQuery();
            if(resultSet.next()){
                return !resultSet.getBoolean(1);
            }
        }

        return true;
    }

    public boolean authenticateUser(String username, String password) throws SQLException {
        String getUser = "SELECT password FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(getUser)){
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                String passwordInDB = rs.getString(1);
                if(passwordInDB.equals(password)){
                    return true;
                }
            }
        }

        return false;

    }

    /**
     * Initializes database and creates all necessary tables if they don't already exist
     * @throws SQLException
     */
    private void initializeDatabase() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
        createTables();
    }

    private void createTables() throws SQLException {
        String createTableRecipes = "CREATE TABLE IF NOT EXISTS recipes (" +
                "recipe_id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "instructions TEXT, " +
                "info TEXT, " +
                "ingredients TEXT, " +
                "portions INTEGER, " +
                "time TEXT, " +
                "genre INTEGER, " +
                "is_published BOOLEAN)";
        String createTableUsers = "CREATE TABLE IF NOT EXISTS users(" +
                "user_id INTEGER PRIMARY KEY, " +
                "username TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableRecipes);
            statement.execute(createTableUsers);
        }
    }
}

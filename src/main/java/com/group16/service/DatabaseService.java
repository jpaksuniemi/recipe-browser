package com.group16.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final String DB_PATH = "./recipeDatabase";
    private Connection connection;

    public List<> getAllRecipes() throws SQLException {
        String selectAllSQL = "SELECT * FROM recipes";
        List<> recipes = new ArrayList<>();
        try (Statement selectAll = connection.createStatement()) {
            ResultSet recipes = selectAll.executeQuery(selectAllSQL);
            while (recipes.next()) {
                String name = recipes.getString("name");
                String instructions = recipes.getString("instructions");
                String info = recipes.getString("info");
                String ingredients = recipes.getString("ingredients");
                int portions = recipes.getInt("portions");
                String time = recipes.getString("time");
                boolean published = recipes.getBoolean("is_published");

                // TODO

            }
        }
        return recipes;
    }

    public void createRecipe() throws SQLException {
    }

    public void createUser() throws SQLException {
    }

    public boolean getUserByUsername(String username, String password) throws SQLException {
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
    public void initializeDatabase() throws SQLException {
        boolean exists = Files.exists(Paths.get(DB_PATH)) && !Files.isDirectory(Paths.get(DB_PATH));
        connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
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
                "is_published BOOLEAN)";
        String createTableUsers = "CREATE TABLE IF NOT EXISTS users(" +
                "user_id INTEGER PRIMARY KEY, " +
                "username TEXT NOT NULL UNIQUE, " +
                "password TEXT NOT NULL UNIQUE)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableRecipes);
            statement.execute(createTableUsers);
        }
    }
}

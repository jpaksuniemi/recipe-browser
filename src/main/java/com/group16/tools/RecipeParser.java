package com.group16.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.group16.model.Recipe;
import com.group16.util.RecipeStyle;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeParser {
    public static List<Recipe> parseRecipes(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = "";
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONArray jsonArray = new JSONArray(sb.toString());
        List<Recipe> recipes = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String name = obj.getString("name");
            String instructions = obj.getString("instructions");
            String info = obj.getString("info");
            String ingredients = obj.getString("ingredients").replace(", ", "\n");
            int portions = obj.getInt("portions");
            String time = obj.getString("time");
            boolean isPublished = obj.getBoolean("is_published");
            RecipeStyle genre = RecipeStyle.fromInt(obj.getInt("genre"));

            Recipe recipe = new Recipe(name, instructions, info, ingredients, portions, time, isPublished, genre);
            recipes.add(recipe);
        }

        return recipes;
    }
}

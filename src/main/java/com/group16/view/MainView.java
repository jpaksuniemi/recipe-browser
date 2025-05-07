package com.group16.view;

import com.group16.controller.MainController;
import com.group16.model.Recipe;
import com.group16.util.AutoScaler;
import com.group16.util.ConstantValues;
import com.group16.util.RecipeStyle;
import com.group16.util.SceneSwitcher;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainView {

    private final MainController controller = new MainController();
    private StackPane rightBox = new StackPane(getPromptBox());
    private ListView<Recipe> recipeList = new ListView<>();
    private TextField searchField = new TextField();
    private ComboBox<String> genre = new ComboBox<>(FXCollections.observableArrayList(Arrays.stream(RecipeStyle.values()).map(RecipeStyle::getString).toArray(String[]::new)));

    public StackPane getView() {
        genre.setPromptText("Valitse suodatin");
        genre.getItems().add(0, "Kaikki");
        rightBox.setPrefSize(ConstantValues.MAINCONTENT_WIDTH, ConstantValues.MAINCONTENT_HEIGHT);
        rightBox.setStyle("-fx-border-style: solid; -fx-border-width: 2; -fx-border-color: #888888;");
        recipeList.getItems().addAll(controller.getRecipes());
        recipeList.setPrefSize(ConstantValues.MAINCONTENT_WIDTH, ConstantValues.MAINCONTENT_HEIGHT);

        recipeList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                rightBox.getChildren().setAll(getRecipeDetailsBox(newVal), getBottomRowButtons(newVal));
            } else {
                rightBox.getChildren().setAll(getPromptBox());
            }
        });


        HBox mainContent = new HBox(10, recipeList, rightBox);
        mainContent.setAlignment(Pos.CENTER);
        VBox root = new VBox(10, getTopButtons(), getSearchBar(), mainContent);
        root.setPrefSize(ConstantValues.BASE_WIDTH, ConstantValues.BASE_HEIGHT);
        root.setPadding(new Insets(10));
        Group group = new Group(root);
        AutoScaler.makeScaleable(root);

        return new StackPane(group);
    }

    private HBox getSearchBar() {
        searchField.setPrefWidth(250);
        searchField.setPromptText("Hae");
        Button searchButton = new Button("🔍");

        searchButton.setOnAction(e -> {
            handleQuery();
        });
        searchField.setOnAction(e -> {
            handleQuery();
        });
        genre.setOnAction(e -> handleQuery());

        HBox searchBox = new HBox(10, searchField, searchButton, genre);
        searchBox.setPadding(new Insets(10));
        searchBox.setAlignment(Pos.CENTER);
        return searchBox;
    }

    private static AnchorPane getTopButtons() {
        AnchorPane pane = new AnchorPane();

        MenuItem addRecipe = new MenuItem("Lisää resepti");
        if(!SceneSwitcher.loginStatus){
            addRecipe.setDisable(true);
        }
        MenuItem myRecipes = new MenuItem("Omat reseptit");
        myRecipes.setDisable(true);
        MenuButton menu = new MenuButton("Menu", null, addRecipe, myRecipes);

        addRecipe.setOnAction(e -> {
            SceneSwitcher.switchToCreateRecipe();
        });

        MenuItem log = new MenuItem("Kirjaudu sisään");
        if(SceneSwitcher.loginStatus){
            log.setText("Kirjaudu ulos");
        }
        MenuButton user = new MenuButton("User", null, log);

        log.setOnAction(e ->{
            SceneSwitcher.loginStatus = false;
            SceneSwitcher.switchToLogin();
        });
            
        AnchorPane.setTopAnchor(menu, 0.0);
        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setRightAnchor(user, 0.0);
        AnchorPane.setTopAnchor(user, 0.0);
        pane.getChildren().addAll(menu, user);
        return pane;
    }

    private VBox getRecipeDetailsBox(Recipe recipe) {
        VBox recipeDetails = new VBox(10);
        recipeDetails.setPadding(new Insets(4));
        Text name = new Text(recipe.getName());
        name.setFont(new Font(24));

        HBox timeAndPortions = new HBox(60);
        timeAndPortions.getChildren().addAll(new Text(recipe.getTime()), new Text(String.valueOf(recipe.getPortions() + " annosta")));

        Text ingredientsTitle = new Text("Ainesosat:");
        ingredientsTitle.setFont(new Font(16));

        Text ingredients = new Text(recipe.getIngredients());

        recipeDetails.getChildren().addAll(name, timeAndPortions, ingredientsTitle, ingredients);
        return recipeDetails;
    }

    /**
     * Help method for completing recipe details -node
     * @param recipe
     * @return
     */
    private AnchorPane getBottomRowButtons(Recipe recipe) {
        AnchorPane bottomRow = new AnchorPane();
        bottomRow.setPadding(new Insets(4));
        Button openButton = new Button("Avaa");

        Text rating = new Text(recipe.getRatingAsStars());
        rating.setFont(new Font(16));

        AnchorPane.setBottomAnchor(openButton, 0.0);
        AnchorPane.setRightAnchor(openButton, 0.0);
        AnchorPane.setBottomAnchor(rating, 0.0);
        AnchorPane.setLeftAnchor(rating, 0.0);

        bottomRow.getChildren().addAll(openButton, rating);
        return bottomRow;
    }

    private void handleQuery() {
        if (searchField.getText().isEmpty()) {
            recipeList.getItems().setAll(controller.getRecipes());
        }

        List<Recipe> results;
        if (genre.getSelectionModel().getSelectedIndex() == 0) {
            results = controller.queryRecipes(searchField.getText(), null);
        } else {
            // -1 to offset the "all" option
            results = controller.queryRecipes(searchField.getText(), RecipeStyle.fromInt(genre.getSelectionModel().getSelectedIndex() - 1));
        }

        if (results.isEmpty()) {
            PopupScreen dialog = new PopupScreen("Haulla ei löytynyt reseptejä");
            recipeList.getItems().setAll(controller.getRecipes());
            dialog.getPopupWindow().showAndWait();
        } else {
            recipeList.getItems().setAll(results);
        }

    }

    private StackPane getPromptBox() {
        StackPane pane = new StackPane();
        Text promptText = new Text("Valitse resepti nähdäksesi sen tiedot");
        promptText.setWrappingWidth(ConstantValues.MAINCONTENT_WIDTH);
        promptText.setFont(new Font(20));
        promptText.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().add(promptText);
        return pane;
    }

}
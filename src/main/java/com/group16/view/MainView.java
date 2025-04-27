package com.group16.view;

import com.group16.util.AutoScaler;
import com.group16.util.ConstantValues;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView {

    private TextArea recipeDetails;

    public StackPane getView() {
        TextField searchField = new TextField();
        searchField.setPrefWidth(250);
        searchField.setPromptText("Hae");
        Button searchButton = new Button("🔍");

        AnchorPane pane = new AnchorPane();
        Button menu = new Button("Menu");
        Button user = new Button("User");
        AnchorPane.setTopAnchor(menu, 0.0);
        AnchorPane.setLeftAnchor(menu, 0.0);
        AnchorPane.setRightAnchor(user, 0.0);
        AnchorPane.setTopAnchor(user, 0.0);
        pane.getChildren().addAll(menu, user);

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));
        searchBox.setAlignment(Pos.CENTER);

        ListView<String> recipeList = new ListView<>();
        recipeList.getItems().add("Makaronilaatikko ★★★★");

        recipeList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if ("Makaronilaatikko ★★★★".equals(newVal)) {
                showRecipeDetails();
            } else {
                recipeDetails.setText("Valitse \"Makaronilaatikko\" nähdäksesi reseptin.");
            }
        });

        recipeDetails = new TextArea();
        recipeDetails.setEditable(false);
        recipeDetails.setWrapText(true);
        recipeDetails.setPrefWidth(250);
        recipeDetails.setPrefHeight(500);
        recipeDetails.setText("Valitse resepti vasemmalta.");

        Button openButton = new Button("Avaa");
        openButton.setOnAction(e -> {
            if (!recipeDetails.getText().contains("Ainekset")) {
                showRecipeDetails();
            }
        });

        VBox rightPanel = new VBox(10, recipeDetails, openButton);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.CENTER_RIGHT);
        rightPanel.setPrefWidth(300);

        HBox mainContent = new HBox(10, recipeList, rightPanel);
        mainContent.setAlignment(Pos.CENTER);
        VBox root = new VBox(10, pane, searchBox, mainContent);
        root.setPrefSize(ConstantValues.BASE_WIDTH, ConstantValues.BASE_HEIGHT);
        root.setPadding(new Insets(10));
        Group group = new Group(root);
        AutoScaler.makeScaleable(root);

        return new StackPane(group);
    }

    private void showRecipeDetails() {
        recipeDetails.setText(
                "Makaronilaatikko\n" +
                        "30–60 min | 4 annosta\n\n" +
                        "Pääruoka\n\n" +
                        "Ainekset:\n" +
                        "- 1 sipuli\n" +
                        "- 200g jauhelihaa\n" +
                        "- 150g makaronia\n" +
                        "- 2 kananmunaa\n" +
                        "- 3 dl maitoa\n" +
                        "- suolaa\n" +
                        "- pippuria\n" +
                        "- juustoraastetta"
        );
    }
}
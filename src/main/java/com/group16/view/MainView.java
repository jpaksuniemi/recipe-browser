package com.group16.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView {

    private TextArea recipeDetails;

    public VBox getView() {
        TextField searchField = new TextField();
        searchField.setPrefWidth(250);
        searchField.setPromptText("Hae");
        Button searchButton = new Button("🔍");

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
        VBox root = new VBox(10, searchBox, mainContent);
        root.setPadding(new Insets(10));

        return root;
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
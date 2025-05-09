package com.group16.view;

import com.group16.controller.RecipeCreationController;
import com.group16.model.Recipe;
import com.group16.util.AutoScaler;
import com.group16.util.RecipeStyle;
import com.group16.util.SceneSwitcher;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreateRecipe{

    private TextArea description = new TextArea();
    private TextArea instructions = new TextArea();
    private TextArea ingredients = new TextArea();
    private TextField time = new TextField();
    private Spinner<Integer> spinner = new Spinner<Integer>(1, 50, 4);
    private CheckBox checkBox = new CheckBox();
    private TextField recipeName = new TextField();
    private Text errorMessage = new Text("");
    private ComboBox<String> genre = new ComboBox<>(FXCollections.observableArrayList(getGenres()));

    private final RecipeCreationController controller = new RecipeCreationController();
    private RecipeStyle[] genreStyle = RecipeStyle.values();

    
    public StackPane getRecipeForm(){
        VBox vbox = new VBox(getBody());
        AutoScaler.makeScaleable(vbox);
        Group group = new Group(vbox);
        return new StackPane(group);
        
    }

    private HBox getTitle(){
        HBox hbox = new HBox();
        Label label = new Label("Lisää resepti");
        label.setFont(new Font("Arial", 30));

        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.TOP_LEFT);

        return hbox;
    }

    private HBox getRecipeNameBox(){
        HBox hbox = new HBox();

        recipeName.setPromptText("Reseptin nimi");
        recipeName.setPrefSize(200, 40);

        hbox.getChildren().add(recipeName);

        return hbox;

    }

    private GridPane getBody() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(getTitle(), 0, 0);
        grid.add(getRecipeNameBox(), 1, 0);

        grid.add(getDescriptionForm(), 0, 2);
        grid.add(getInstructions(), 1, 2);
        grid.add(getIngredients(), 2, 2);

        Button returnButton = new Button("Palaa takaisin");
        returnButton.setPrefSize(200, 30);
        returnButton.setId("back");
        StackPane returnButtonPane = new StackPane(returnButton);
        returnButtonPane.setAlignment(Pos.CENTER);

        grid.add(returnButtonPane, 0, 4);

        returnButton.setOnAction(e -> {
            SceneSwitcher.switchToMainView();
        });

        grid.add(getPublicCheckBox(), 1, 4);

        Button addButton = new Button("Lisää");
        addButton.setPrefSize(200, 30);
        addButton.setId("login");
        StackPane addButtonPane = new StackPane(addButton);
        addButtonPane.setAlignment(Pos.CENTER);

        grid.add(addButtonPane, 2, 4);

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(20));

        addButton.setOnAction(e -> {
            if(!recipeName.getText().isEmpty() && !description.getText().isEmpty() && !instructions.getText().isEmpty() && !ingredients.getText().isEmpty() && !time.getText().isEmpty() && genre.getSelectionModel().getSelectedItem() != null ){
               handleAddRecipe(recipeName.getText(), description.getText(), ingredients.getText(), instructions.getText(), time.getText(), spinner.getValue(), checkBox.isSelected(), genre.getSelectionModel().getSelectedIndex()); 
            } else {
                errorMessage.setText("Täytä kaikki kentät");
            }
        });

        errorMessage.setFill(Color.RED);
        
        errorMessage.setTextAlignment(TextAlignment.CENTER);
        grid.add(errorMessage, 2, 3);

        return grid;
    }

    private VBox getDescriptionForm() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label label = new Label("Lyhyt kuvaus reseptistä:");
        label.setFont(new Font("Arial", 20));

        description.setPromptText("...");
        description.setPrefSize(200, 200);

        vbox.getChildren().addAll(label, description, getCookingTime());
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    private VBox getInstructions(){
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label label = new Label("Valmistusohjeet:");
        label.setFont(new Font("Arial", 20));

        instructions.setPromptText("...");
        instructions.setPrefSize(200, 200);

        vbox.getChildren().addAll(label, instructions, getGenreBox());
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    private VBox getIngredients(){
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label label = new Label("Ainesosat:");
        label.setFont(new Font("Arial", 20));

        ingredients.setPromptText("...");
        ingredients.setPrefSize(200, 200);

        vbox.getChildren().addAll(label, ingredients, getPortions());
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    private HBox getCookingTime(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label("Valmistusaika:");
        label.setFont(new Font("Arial", 20));

        time.setPrefSize(40, 10);

        Label label2 = new Label("min");
        label.setFont(new Font("Arial", 20));

        hbox.getChildren().addAll(label, time, label2);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private HBox getPortions(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label("Annokset:");
        label.setFont(new Font("Arial", 20));

        spinner.setPrefSize(60, 10);

        hbox.getChildren().addAll(label, spinner);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private HBox getGenreBox(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label("Tyyli: ");
        label.setFont(new Font("Arial", 20));

        genre.setPromptText("Valitse tyyli");

        hbox.getChildren().addAll(label, genre);

        return hbox;

    }

    private String[] getGenres(){
        String[] genres = new String[RecipeStyle.values().length];
        int index = 0;

        for(RecipeStyle genre : RecipeStyle.values()){
            genres[index] = genre.getString();
            index ++;
        }

        return genres;
    }

    private HBox getPublicCheckBox(){
        HBox hbox = new HBox();
        hbox.setSpacing(10);

        Label label = new Label("Julkinen?:");
        label.setFont(new Font("Arial", 20));

        hbox.getChildren().addAll(label, checkBox);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private void handleAddRecipe(String name, String description, String ingredients, String instructions, String time, int portions, boolean publish, int genreIndex){
        
        if (isNumber(time)){
            String string = "%smin";
            String timeString = String.format(string, time);
            Recipe recipe = new Recipe(name, instructions, description, ingredients, portions, timeString, publish, genreStyle[genreIndex]);
            int status = controller.addRecipe(recipe);

            if (status == RecipeCreationController.SUCCESS) {
                PopupScreen dialog = new PopupScreen("Reseptin lisääminen onnistui");
                if (dialog.getPopupWindow().showAndWait().isPresent()) {
                    SceneSwitcher.switchToMainView();
                }
            } else if (status == RecipeCreationController.ERROR) {
                errorMessage.setText("Reseptin lisääminen epäonnistui");
            }
        } else errorMessage.setText("Ilmoita aika numeroina");
    }

    private boolean isNumber(String time){
        for(int i = 0; i < time.length(); i++){
            if(!Character.isDigit(time.charAt(i))){
                return false;
            }
        } 
        return true;
    }
}

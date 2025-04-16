package com.group16.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class Login {

    private Label errorMessage = new Label("Virhe viesti");

    public BorderPane getLoginScreen() {
        BorderPane pane = new BorderPane();
        pane.setTop(getTitle());
        pane.setCenter(getBody());
        return pane;
    }

    private HBox getTitle() {
        HBox hbox = new HBox();
        Label label = new Label("Tervetuloa sovellukseen!");
        label.setFont(new Font("Arial", 30));
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 0, 20, 0));
        return hbox;
    }

    private GridPane getBody() {
        GridPane grid = new GridPane();
        grid.setHgap(70);
        grid.setVgap(30);

        grid.add(getForm(), 0, 0);
        grid.add(getOtherOptions(), 1, 0);
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(20));

        return grid;
    }

    private VBox getForm() {
        VBox vbox = new VBox();
        vbox.setSpacing(10);

        Label label = new Label("Kirjaudu sisään");
        label.setFont(new Font("Arial", 20));

        TextField username = new TextField();
        username.setPromptText("Käyttäjänimi");
        PasswordField password = new PasswordField();
        password.setPromptText("Salasana");

        Button login = new Button("Kirjaudu");

        vbox.getChildren().addAll(label, username, password, login, errorMessage);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    private VBox getOtherOptions() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Label label = new Label("Oletko uusi käyttäjä?");
        label.setFont(new Font("Arial", 20));

        Button createAccount = new Button("Luo tili");
        Button asAGuest = new Button("Jatka vierailijana");

        vBox.getChildren().addAll(label, createAccount, asAGuest);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
}

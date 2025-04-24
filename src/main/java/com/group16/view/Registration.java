package com.group16.view;

import com.group16.controller.RegistrationController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Registration {

    private final RegistrationController controller = new RegistrationController();
    private Label errorMessage = new Label("Virhe viesti");

    public BorderPane getLoginForm() {
        BorderPane pane = new BorderPane();
        pane.setCenter(getForm());
        return pane;
    }

    private HBox getTitle() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        Label title = new Label("Luo tili");
        title.setFont(new Font("Arial", 30));
        hbox.getChildren().add(title);

        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private HBox getErrorMessage() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 12, 15, 12));

        hbox.getChildren().add(errorMessage);
        hbox.setAlignment(Pos.CENTER);

        return hbox;
    }

    private GridPane getForm() {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(10);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Käyttäjänimi");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Salasana");
        PasswordField confirmInput = new PasswordField();
        confirmInput.setPromptText("Vahvista salasana");

        Button registerButton = new Button("Rekisteröidy");
        registerButton.setPrefSize(200, 20);
        registerButton.autosize();

        PopupScreen information = new PopupScreen("Rekisteröinti onnistui");

        registerButton.setOnAction((ActionEvent event) -> {
            information.getPopupWindow().showAndWait();
            handleRegistration(usernameInput, passwordInput);
        });

        pane.add(getTitle(), 0, 0);
        pane.add(usernameInput, 0, 1);
        pane.add(passwordInput, 0, 2);
        pane.add(confirmInput, 0, 3);
        pane.add(registerButton, 0, 4);
        pane.add(getErrorMessage(), 0, 5);
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

    private void handleRegistration(TextField usernameInput, PasswordField passwordInput) {
        errorMessage.setText(controller.registerUser(usernameInput.getText(), passwordInput.getText()));
        System.out.println("Painoi nappia");
    }
}

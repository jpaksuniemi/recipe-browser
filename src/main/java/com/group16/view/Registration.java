package com.group16.view;

import com.group16.controller.RegistrationController;
import com.group16.util.AutoScaler;
import com.group16.util.SceneSwitcher;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Registration {

    private final RegistrationController controller = new RegistrationController();
    private Text errorMessage = new Text("");

    public StackPane getRegistrationForm() {
        VBox vbox = getForm();
        AutoScaler.makeScaleable(vbox);
        Group group = new Group(vbox);
        return new StackPane(group);
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

    private VBox getForm() {
        VBox box = new VBox();
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER);

        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Käyttäjänimi");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Salasana");
        PasswordField confirmInput = new PasswordField();
        confirmInput.setPromptText("Vahvista salasana");

        Button registerButton = new Button("Rekisteröidy");
        registerButton.setId("login");
        registerButton.setPrefWidth(200);

        registerButton.setOnAction((ActionEvent event) -> {
            if (usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty() || confirmInput.getText().isEmpty()) {
                errorMessage.setText("Täytä kaikki kentät");
            } else if (!passwordInput.getText().equals(confirmInput.getText())) {
                errorMessage.setText("Salasanat eivät täsmää");
            } else {
                handleRegistration(usernameInput.getText(), passwordInput.getText());
            }
        });
        usernameInput.setOnAction(e -> registerButton.fire());
        passwordInput.setOnAction(e -> registerButton.fire());
        confirmInput.setOnAction(e -> registerButton.fire());

        Button returnButton = new Button("Palaa takaisin");
        returnButton.setId("back");
        returnButton.setOnAction(e ->{
            SceneSwitcher.switchToLogin();
        });
        returnButton.setPrefSize(registerButton.getPrefWidth(), registerButton.getPrefHeight());

        errorMessage.setFill(Color.RED);
        errorMessage.setWrappingWidth(box.getWidth());
        box.getChildren().addAll(getTitle(), usernameInput, passwordInput, confirmInput, registerButton, returnButton, getErrorMessage());

        return box;
    }

    private void handleRegistration(String username, String password) {
        int status = controller.registerUser(username, password);
        if (status == RegistrationController.SUCCESS) {
            PopupScreen popupScreen = new PopupScreen("Rekisteröinti onnistui");
            if (popupScreen.getPopupWindow().showAndWait().isPresent()) {
                SceneSwitcher.setLoginStatus(true);
                SceneSwitcher.switchToMainView();
            }
        } else if (status == RegistrationController.FAILURE) {
            errorMessage.setText("Käyttäjänimi on jo käytössä");
        } else {
            errorMessage.setText("Odottamaton virhe");
        }
    }
}

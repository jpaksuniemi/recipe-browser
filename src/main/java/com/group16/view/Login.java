package com.group16.view;

import com.group16.controller.LoginController;
import com.group16.util.AutoScaler;
import com.group16.util.SceneSwitcher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Optional;

public class Login {

    private Text errorMessage = new Text("");
    private LoginController controller = new LoginController();

    public StackPane getLoginScreen() {
        VBox pane = new VBox(getTitle(), getBody());
        AutoScaler.makeScaleable(pane);
        Group group = new Group(pane);
        return new StackPane(group);
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
        login.setOnAction(e -> {
            if (!username.getText().isEmpty() || !password.getText().isEmpty()) {
                handleLogin(username.getText(), password.getText());
            } else {
                errorMessage.setText("Täytä kaikki kentät");
            }
        });

        errorMessage.setFill(Color.RED);
        errorMessage.wrappingWidthProperty().bind(vbox.widthProperty());
        errorMessage.setTextAlignment(TextAlignment.CENTER);
        vbox.getChildren().addAll(label, username, password, login, errorMessage);
        vbox.setAlignment(Pos.TOP_CENTER);
        return vbox;
    }

    private VBox getOtherOptions() {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        Label label = new Label("Oletko uusi käyttäjä?");
        label.setFont(new Font("Arial", 20));

        Button createAccount = new Button("Luo tili");
        createAccount.setOnAction(e -> {
            SceneSwitcher.switchToRegistration();
        });

        Button asAGuest = new Button("Jatka vierailijana");
        asAGuest.setOnAction(e -> {
            SceneSwitcher.switchToMainView();
        });

        vBox.getChildren().addAll(label, createAccount, asAGuest);
        vBox.setAlignment(Pos.TOP_CENTER);
        return vBox;
    }

    private void handleLogin(String username, String password) {
        int status = controller.loginUser(username, password);
        if (status == LoginController.AUTHENTICATED) {
            PopupScreen dialog = new PopupScreen("Kirjautuminen onnistui");
            if (dialog.getPopupWindow().showAndWait().isPresent()) {
                SceneSwitcher.switchToMainView();
            }
        } else if (status == LoginController.NOT_FOUND) {
            errorMessage.setText("Väärä käyttäjänimi tai salasana");
        } else {
            errorMessage.setText("Odottamaton virhe");
        }
    }
}

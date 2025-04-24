package com.group16.view;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class PopupScreen {

    private final String message;

    public PopupScreen(String message) {
        this.message = message;
    }

    public Alert getPopupWindow() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.setHeaderText(null);
        alert.setTitle("Ilmoitus");
        return alert;
    }
}

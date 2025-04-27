package com.group16;

import com.group16.util.SceneSwitcher;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        SceneSwitcher.initialize(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
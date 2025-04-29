package com.group16.util;

import com.group16.view.Login;
import com.group16.view.MainView;
import com.group16.view.Registration;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    private static Stage stage;
    private static Scene currentScene;

    public static void initialize(Stage stage) {
        SceneSwitcher.stage = stage;
        Scene login = new Scene(new Login().getLoginScreen(), ConstantValues.BASE_WIDTH, ConstantValues.BASE_HEIGHT);

        AutoScaler.setActiveScene(login);
        stage.setScene(login);
        currentScene = login;
    }

    public static void switchToRegistration() {
        Scene registration = new Scene(new Registration().getRegistrationForm(), currentScene.getWidth(), currentScene.getHeight());
        stage.setScene(registration);
        AutoScaler.setActiveScene(registration);
        currentScene = registration;
    }

    public static void switchToLogin() {
        Scene login = new Scene(new Login().getLoginScreen(), currentScene.getWidth(), currentScene.getHeight());
        stage.setScene(login);
        AutoScaler.setActiveScene(login);
        currentScene = login;
    }

    public static void switchToMainView() {
        Scene mainView = new Scene(new MainView().getView(), currentScene.getWidth(), currentScene.getHeight());
        stage.setScene(mainView);
        AutoScaler.setActiveScene(mainView);
        currentScene = mainView;
    }
}

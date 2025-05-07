package com.group16.util;

import java.util.ArrayList;

import com.group16.view.CreateRecipe;
import com.group16.view.Login;
import com.group16.view.MainView;
import com.group16.view.Registration;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    private static Stage stage;
    private static Scene currentScene;
    public static boolean loginStatus = false;

    public static void initialize(Stage stage) {
        SceneSwitcher.stage = stage;
        Scene login = new Scene(new Login().getLoginScreen(), ConstantValues.BASE_WIDTH, ConstantValues.BASE_HEIGHT);
        System.out.println(System.getProperty("user.dir"));
        login.getStylesheets().add(("/style.css"));

        AutoScaler.setActiveScene(login);
        stage.setScene(login);
        currentScene = login;
    }

    public static void switchToRegistration() {
        Scene registration = new Scene(new Registration().getRegistrationForm(), currentScene.getWidth(), currentScene.getHeight());
        registration.getStylesheets().add(("/style.css"));
        stage.setScene(registration);
        AutoScaler.setActiveScene(registration);
        currentScene = registration;
    }

    public static void switchToLogin() {
        Scene login = new Scene(new Login().getLoginScreen(), currentScene.getWidth(), currentScene.getHeight());
        login.getStylesheets().add(("/style.css"));
        stage.setScene(login);
        AutoScaler.setActiveScene(login);
        currentScene = login;
    }

    public static void switchToMainView() {
        Scene mainView = new Scene(new MainView().getView(), currentScene.getWidth(), currentScene.getHeight());
        mainView.getStylesheets().add(("/style.css"));
        stage.setScene(mainView);
        AutoScaler.setActiveScene(mainView);
        currentScene = mainView;
    }

    public static void switchToCreateRecipe(){
        Scene createRecipe = new Scene(new CreateRecipe().getRecipeForm(), currentScene.getWidth(), currentScene.getHeight());
        createRecipe.getStylesheets().add(("/style.css"));
        stage.setScene(createRecipe);
        AutoScaler.setActiveScene(createRecipe);
        currentScene = createRecipe;
    }

}

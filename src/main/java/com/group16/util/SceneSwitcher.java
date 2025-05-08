package com.group16.util;

import com.group16.view.CreateRecipe;
import com.group16.view.Login;
import com.group16.view.MainView;
import com.group16.view.Registration;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {

    private static Stage stage;
    private static Scene currentScene;
    private static boolean loginStatus = false;

    public static void initialize(Stage stage) {
        SceneSwitcher.stage = stage;
        Scene mainScene = new Scene(new Login().getLoginScreen(), ConstantValues.BASE_WIDTH, ConstantValues.BASE_HEIGHT);
        System.out.println(System.getProperty("user.dir"));
        mainScene.getStylesheets().add(("/style.css"));

        stage.setMinHeight(ConstantValues.BASE_HEIGHT);
        stage.setMinWidth(ConstantValues.BASE_WIDTH);

        stage.setTitle("Reseptiselain");

        AutoScaler.setScene(mainScene);
        stage.setScene(mainScene);
        currentScene = mainScene;
    }

    public static void switchToRegistration() {
        currentScene.setRoot(new Registration().getRegistrationForm());
    }

    public static void switchToLogin() {
        currentScene.setRoot(new Login().getLoginScreen());
    }

    public static void switchToMainView() {
        currentScene.setRoot(new MainView().getView());
    }

    public static void switchToCreateRecipe(){
        currentScene.setRoot(new CreateRecipe().getRecipeForm());
    }

    public static boolean isLoginStatus() {
        return loginStatus;
    }

    public static void setLoginStatus(boolean loginStatus) {
        SceneSwitcher.loginStatus = loginStatus;
    }
}

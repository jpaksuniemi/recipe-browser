package com.group16.controller;

import com.group16.service.DatabaseService;

public class RegistrationController {

    private final DatabaseService databaseService = DatabaseService.getInstance();

    public RegistrationController() {}

    public String registerUser(String username, String password) {
        try {
            databaseService.addUser(username, password);
            System.out.println("User " + username + " registered successfully");
            return "Onnistui";
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
            return "Virhe rekisteröinnissä";
        }
    }
}

package com.group16.controller;

import com.group16.service.DatabaseService;

public class RegistrationController {

    private final DatabaseService databaseService = DatabaseService.getInstance();
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
    public static final int ERROR = -1;

    public RegistrationController() {}

    public int registerUser(String username, String password) {
        try {
            if (databaseService.addUser(username, password)) return SUCCESS;
            else return FAILURE;
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
            return ERROR;
        }
    }
}

package com.group16.controller;

import com.group16.service.DatabaseService;

public class LoginController {

    public static final int AUTHENTICATED = 1;
    public static final int NOT_FOUND = 0;
    public static final int ERROR = -1;
    private final DatabaseService databaseService = DatabaseService.getInstance();

    public LoginController() {}

    public int loginUser(String username, String password) {
        try {
            if (databaseService.authenticateUser(username, password)) return AUTHENTICATED;
            else return NOT_FOUND;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ERROR;
        }

    }

}

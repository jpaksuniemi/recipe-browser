package com.group16;

import com.group16.service.DatabaseService;

public class Main {
    public static void main(String[] args) {
        DatabaseService db = new DatabaseService();

        try {
            db.initializeDatabase();
        } catch (Exception e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}

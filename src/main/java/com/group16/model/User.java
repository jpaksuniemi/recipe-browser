package com.group16.model;

public class User {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username != null){
            this.username = username;
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null){
            this.password = password;
        }
    }
}

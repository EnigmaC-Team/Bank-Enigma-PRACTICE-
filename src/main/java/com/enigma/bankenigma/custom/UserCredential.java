package com.enigma.bankenigma.custom;

public class UserCredential {
     private String username;
     private String password;

    public UserCredential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCredential() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

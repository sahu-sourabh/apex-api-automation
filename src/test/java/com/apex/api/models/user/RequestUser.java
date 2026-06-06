package com.apex.api.models.user;

public class RequestUser {
    private String username;
    private String email;
    private String password;

    // Required No-Args Constructor
    public RequestUser() {
    }

    // Fluent Setters
    public RequestUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public RequestUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public RequestUser setPassword(String password) {
        this.password = password;
        return this;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
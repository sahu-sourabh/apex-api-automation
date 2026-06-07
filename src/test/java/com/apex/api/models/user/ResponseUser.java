package com.apex.api.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseUser {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public ResponseUser() {}

    public ResponseUser setEmail(String email) {
        this.email = email;
        return this;
    }
    public ResponseUser setToken(String token) {
        this.token = token;
        return this;
    }
    public ResponseUser setUsername(String username) {
        this.username = username;
        return this;
    }
    public ResponseUser setBio(String bio) {
        this.bio = bio;
        return this;
    }
    public ResponseUser setImage(String image) {
        this.image = image;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }
}
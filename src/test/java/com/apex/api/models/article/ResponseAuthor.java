package com.apex.api.models.article;

public class ResponseAuthor {
    String username;
    String bio;
    String image;
    Boolean following;

    // Required No-Args Constructor
    public ResponseAuthor() {

    }

    // Fluent Setters
    public ResponseAuthor setUsername(String username) {
        this.username = username;
        return this;
    }

    public ResponseAuthor setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public ResponseAuthor setImage(String image) {
        this.image = image;
        return this;
    }

    public ResponseAuthor setFollowing(Boolean following) {
        this.following = following;
        return this;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public Boolean getFollowing() {
        return following;
    }
}

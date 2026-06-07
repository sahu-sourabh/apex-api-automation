package com.apex.api.models.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuthor {
    private String username;
    private String bio;
    private String image;
    private Boolean following;

    public ResponseAuthor() {}

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
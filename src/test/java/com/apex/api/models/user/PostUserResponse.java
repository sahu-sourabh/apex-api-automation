package com.apex.api.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUserResponse {
    private ResponseUser user;

    // Required No-Args Constructor
    public PostUserResponse() {
    }

    // Fluent Setter
    public PostUserResponse setUser(ResponseUser user) {
        this.user = user;
        return this;
    }

    // Getter
    public ResponseUser getUser() {
        return user;
    }
}
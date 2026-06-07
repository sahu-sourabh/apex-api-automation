package com.apex.api.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostUserResponse {
    private ResponseUser user;

    public PostUserResponse() {}

    public PostUserResponse setUser(ResponseUser user) {
        this.user = user;
        return this;
    }

    public ResponseUser getUser() {
        return user;
    }
}
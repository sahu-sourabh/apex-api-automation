package com.apex.api.models.user;

public class PostUserRequest {
    private RequestUser user;

    // Required No-Args Constructor
    public PostUserRequest() {
    }

    // Fluent Setter
    public PostUserRequest setuser(RequestUser user) {
        this.user = user;
        return this;
    }

    // Getter
    public RequestUser getUser() {
        return user;
    }
}
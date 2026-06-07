package com.apex.api.models.user;

public class PostUserRequest {
    private RequestUser user;

    public PostUserRequest() {}

    public PostUserRequest setUser(RequestUser user) {
        this.user = user;
        return this;
    }

    public RequestUser getUser() {
        return user;
    }
}
package com.apex.api.models.article;

import java.util.List;

public class RequestArticle {
    private String title;
    private String description;
    private String body;
    private List<String> tagList;

    public RequestArticle() {}

    public RequestArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public RequestArticle setDescription(String description) {
        this.description = description;
        return this;
    }

    public RequestArticle setBody(String body) {
        this.body = body;
        return this;
    }

    public RequestArticle setTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTagList() {
        return tagList;
    }
}
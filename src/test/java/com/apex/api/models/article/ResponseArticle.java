package com.apex.api.models.article;

import java.util.List;

public class ResponseArticle {
    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    String createdAt;
    String updatedAt;
    Boolean favorited;
    Integer favoritesCount;
    ResponseAuthor author;

    // Required No-Args Constructor
    public ResponseArticle() {
    }

    // Fluent Setters
    public ResponseArticle setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public ResponseArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public ResponseArticle setDescription(String description) {
        this.description = description;
        return this;
    }

    public ResponseArticle setBody(String body) {
        this.body = body;
        return this;
    }

    public ResponseArticle setTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

    public ResponseArticle setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResponseArticle setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ResponseArticle setFavorited(Boolean favorited) {
        this.favorited = favorited;
        return this;
    }

    public ResponseArticle setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
        return this;
    }

    public ResponseArticle setAuthor(ResponseAuthor author) {
        this.author = author;
        return this;
    }

    // Setters
    public String getSlug() {
        return slug;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getFavorited() {
        return favorited;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public ResponseAuthor getAuthor() {
        return author;
    }

}

package com.apex.api.models.article;

public class PostArticleRequest {
    private RequestArticle article;

    // Required No-Args Constructor
    public PostArticleRequest() {
    }

    // Fluent Setter
    public PostArticleRequest setArticle(RequestArticle article) {
        this.article = article;
        return this;
    }

    // Getter
    public RequestArticle getArticle() {
        return article;
    }
}

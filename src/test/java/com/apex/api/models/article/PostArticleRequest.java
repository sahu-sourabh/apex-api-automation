package com.apex.api.models.article;

public class PostArticleRequest {
    private RequestArticle article;

    public PostArticleRequest() {}

    public PostArticleRequest setArticle(RequestArticle article) {
        this.article = article;
        return this;
    }

    public RequestArticle getArticle() {
        return article;
    }
}
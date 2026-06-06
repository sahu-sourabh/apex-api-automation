package com.apex.api.models.article;

public class PostArticleResponse {
    ResponseArticle article;
    
    // Required No-Args Constructor
    public PostArticleResponse() {}

    // Fluent Setter
    public PostArticleResponse setArticle(ResponseArticle article){
        this.article = article;
        return this;
    }

    // Getter
    public ResponseArticle getArticle() {
        return article;
    }
}

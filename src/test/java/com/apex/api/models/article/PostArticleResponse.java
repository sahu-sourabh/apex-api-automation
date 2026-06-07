package com.apex.api.models.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostArticleResponse {
    private ResponseArticle article;
    
    public PostArticleResponse() {}

    public PostArticleResponse setArticle(ResponseArticle article){
        this.article = article;
        return this;
    }

    public ResponseArticle getArticle() {
        return article;
    }
}
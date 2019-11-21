package cz.falcon9.redact.backend.services;

import java.util.List;

import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleService {

    List<Article> getAllArticles(String authorId);
    
}

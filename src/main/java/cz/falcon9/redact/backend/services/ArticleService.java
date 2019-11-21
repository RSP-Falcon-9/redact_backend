package cz.falcon9.redact.backend.services;

import java.util.List;

import org.springframework.core.io.FileSystemResource;

import cz.falcon9.redact.backend.data.dtos.author.CreateArticleRequest;
import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleService {

    List<Article> getAllArticles(String authorId);
    
    Article insertArticle(CreateArticleRequest request);
    
    FileSystemResource getArticleFile(String articleId, Integer version);
    
}

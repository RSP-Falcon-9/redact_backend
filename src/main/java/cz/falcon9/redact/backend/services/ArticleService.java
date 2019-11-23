package cz.falcon9.redact.backend.services;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleService {

    List<Article> getAllArticles();
    List<Article> getAllArticles(String authorId);
    List<Article> getNewArticles();
    List<Article> getAllArticlesForReviewer(String reviewerId);
    Article insertNewArticle(String name, MultipartFile file);
    Article insertNewArticleVersion(String id, MultipartFile file);
    //Article insertNewArticleVersion();
    Article getArticle(String articleId);
    FileSystemResource getArticleFile(String articleId, Integer version);
    void updateArticle(Article article);
    
}

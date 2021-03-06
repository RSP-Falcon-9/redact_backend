package cz.falcon9.redact.backend.services;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;
import cz.falcon9.redact.backend.data.models.articles.Article;

public interface ArticleService {
    
    List<Article> getAllArticles();
    List<Article> getAllArticles(String authorId);
    List<Article> getNewArticles();
    List<Article> getAllArticlesForReviewer(String reviewerId);
    Article insertNewArticle(String name, Integer editionNumber, MultipartFile file);
    Article insertNewArticleVersion(String id, MultipartFile file);
    Article getArticle(String articleId);
    void removeArticle(String articleId, Integer version);
    void removeArticle(String articleId);
    FileSystemResource getArticleFile(String articleId, Integer version);
    void updateArticle(Article article);
    void assignReviewerToArticle(String articleId, Integer version, String reviewerId);
    void setArticleStatus(String articleId, Integer version, ArticleStatus status);
    void setArticleEdition(String articleId, Integer editionNumber);
    
}

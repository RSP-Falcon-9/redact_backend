package cz.falcon9.redact.backend.services;

import cz.falcon9.redact.backend.data.articles.ArticleReviewForm;

public interface ReviewerService {

    void reviewArticle(String articleId, Integer version, ArticleReviewForm form);
    
}

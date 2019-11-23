package cz.falcon9.redact.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.repositories.UserRepository;

public class EditorServiceImpl implements EditorService {

    @Autowired
    ArticleService articleServ;
    
    @Autowired
    UserRepository userRepo;

    @Override
    @Transactional
    public void assignReviewerToArticle(String articleId, Integer version, String reviewerId) {
        Article article = articleServ.getArticle(articleId);
        Optional<ArticleVersion> optionalArticleVersion = article.getVersions().stream().filter(articleVer -> articleVer.getVersion() == version).findFirst();
        
        if (!optionalArticleVersion.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find article version %s.", version));
        }
        
        Optional<User> optionalReviewer = userRepo.findById(reviewerId);
        if (optionalReviewer.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find reviewer with id %s.", reviewerId));
        }
        
        ArticleVersion articleVersion = optionalArticleVersion.get();
        Optional<ArticleReview> optionalArticleReview = articleVersion.getReviews().stream().filter(articleVer -> articleVer.getReviewer().getUserName() == reviewerId).findFirst();
        if (optionalArticleReview.isPresent()) {
            throw new InvalidArgumentException(String.format("Reviewer %s already reviewed this article %s and version %s.", reviewerId, articleId, version));
        }
        
        articleVersion.getReviews().add(ArticleReview.builder()
                .withArticleId(articleId)
                .withVersion(version)
                .withReviewStatus(ArticleReviewStatus.NEW)
                .withReviewer(optionalReviewer.get())
                .build());
       
        articleServ.updateArticle(article);
    }

}

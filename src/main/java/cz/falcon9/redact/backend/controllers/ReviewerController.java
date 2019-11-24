package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.articles.ArticleReviewForm;
import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.reviewer.GetReviewerArticleDetailResponse;
import cz.falcon9.redact.backend.data.dtos.reviewer.GetReviewerArticlesResponse;
import cz.falcon9.redact.backend.data.dtos.reviewer.ReviewArticleRequest;
import cz.falcon9.redact.backend.data.dtos.reviewer.ReviewerArticle;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.services.ArticleService;
import cz.falcon9.redact.backend.services.ReviewerService;

@RestController
@RequestMapping("/reviewer")
@Secured("ROLE_REVIEWER")
public class ReviewerController {

    @Autowired
    ArticleService articleServ;
    
    @Autowired
    ReviewerService reviewerServ;
    
    @GetMapping("/articles")
    @Transactional
    public BaseDto<GetReviewerArticlesResponse> handleGetPendingReviews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        
        List<ReviewerArticle> reviewerArticles = new ArrayList<>();
        List<Article> articlesForReviewer = articleServ.getAllArticlesForReviewer(currentUserName);
        for (Article article : articlesForReviewer) {
            List<ArticleVersion> versions = article.getVersions();
            
            for (ArticleVersion version : versions) {
                Optional<ArticleReview> optionalReview = version.getReviews().stream().filter(rev ->
                    rev.getReviewer().getUserName().equals(currentUserName)).findFirst();
                if (optionalReview.isPresent()) {
                    ArticleReview review = optionalReview.get();

                    reviewerArticles.add(ReviewerArticle.builder()
                            .withId(article.getId())
                            .withName(article.getName())
                            .withVersion(version.getVersion())
                            .withFileName(version.getFileName())
                            .withPublishDate(version.getPublishDate())
                            .withStatus(review.getReviewStatus())
                            .build());
                }
            }
        }
        
        return new BaseDto<GetReviewerArticlesResponse>(
                GetReviewerArticlesResponse.builder()
                        .withArticles(reviewerArticles)
                        .build(),
                String.format("Successfully got articles authored by %s.", currentUserName));
       
    }
    
    @GetMapping("/article/{id}/{version}")
    public BaseDto<GetReviewerArticleDetailResponse> handleGetReviewerArticleDetail(@PathVariable String id, @PathVariable Integer version) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Article article = articleServ.getArticle(id);
        ArticleVersion articleVersion = article.getVersion(version);
        if (articleVersion == null) {
            throw new ArgumentNotFoundException(String.format("Cannot find specified version %s for article %s!", version, id));
        }
        
        Optional<ArticleReview> optionalReview = articleVersion.getReviews().stream().filter(rev ->
        rev.getReviewer().getUserName().equals(currentUserName)).findFirst();
        if (!optionalReview.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find review for version %s of article %s!", version, id));
        }
        
        ArticleReview review = optionalReview.get();
 
        return new BaseDto<GetReviewerArticleDetailResponse>(GetReviewerArticleDetailResponse.builder()
                .withName(article.getName())
                .withReviewStatus(review.getReviewStatus())
                .withInterest(review.getInterest())
                .withOriginality(review.getOriginality())
                .withSpecializationLevel(review.getSpecializationLevel())
                .withLanguageLevel(review.getLanguageLevel())
                .withReviewDate(review.getReviewDate())
                .build(),
                String.format("Successfully got article with id %s and version %s.", id, version));
    }
    
    @PostMapping("/review/{reviewId}")
    public BaseDto<Void> handleReviewArticle(@PathVariable String reviewId,
            @Valid @RequestBody ReviewArticleRequest request) {
        
        reviewerServ.reviewArticle(reviewId, ArticleReviewForm.builder()
                .withInterest(request.getInterest())
                .withOriginality(request.getOriginality())
                .withSpecializationLevel(request.getSpecializationLevel())
                .withLanguageLevel(request.getLanguageLevel())
                .withComment(request.getComment())
                .build());
        
        return new BaseDto<Void>(String.format("Successfully reviewed %s.", reviewId));
    }
    
}

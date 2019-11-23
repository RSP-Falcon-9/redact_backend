package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.reviewer.GetReviewerArticlesResponse;
import cz.falcon9.redact.backend.data.dtos.reviewer.ReviewerArticle;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.services.ArticleService;

@RestController
@RequestMapping("/reviewer")
@Secured("ROLE_REVIEWER")
public class ReviewerController {

    @Autowired
    ArticleService articleServ;
    
    @GetMapping("/reviews")
    public BaseDto<GetReviewerArticlesResponse> handleGetPendingReviews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        
        List<ReviewerArticle> reviewerArticles = new ArrayList<>();
        List<Article> articlesForReviewer = articleServ.getAllArticlesForReviewer(currentUserName);
        for (Article article : articlesForReviewer) {
            List<ArticleVersion> versions = article.getVersions();
            
            for (ArticleVersion version : versions) {
                Optional<ArticleReview> optionalReview = version.getReviews().stream().filter(rev -> rev.getReviewer().getUserName() == currentUserName).findFirst();
                if (optionalReview.isPresent()) {
                    //ArticleReview review = optionalReview.get();

                    reviewerArticles.add(ReviewerArticle.builder()
                            .withId(article.getId())
                            .withName(article.getName())
                            .withVersion(version.getVersion())
                            .withFileName(version.getFileName())
                            .withPublishDate(version.getPublishDate())
                            .withStatus(version.getStatus())
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
    
}

package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;
import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.editor.EditorArticle;
import cz.falcon9.redact.backend.data.dtos.editor.EditorArticleReview;
import cz.falcon9.redact.backend.data.dtos.editor.GetEditorArticleDetail;
import cz.falcon9.redact.backend.data.dtos.editor.GetEditorArticlesResponse;
import cz.falcon9.redact.backend.data.dtos.editor.GetReviewersResponse;
import cz.falcon9.redact.backend.data.dtos.editor.Reviewer;
import cz.falcon9.redact.backend.data.dtos.editor.SetReviewerToArticleRequest;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.services.ArticleService;
import cz.falcon9.redact.backend.services.EditorService;
import cz.falcon9.redact.backend.services.ReviewService;

@RestController
@RequestMapping("/editor")
@Secured("ROLE_EDITOR")
public class EditorController {

    @Autowired
    ArticleService articleServ;
    
    @Autowired
    EditorService editorServ;
    
    @Autowired
    ReviewService reviewServ;
    
    @PostMapping("/reviewer/assign/{articleId}/{version}")
    public BaseDto<Void> handleAssignReviewerToArticle(@PathVariable String articleId, @PathVariable Integer version,
            @RequestBody SetReviewerToArticleRequest request) {
        articleServ.assignReviewerToArticle(articleId, version, request.getReviewerId());
        
        return new BaseDto<Void>(String.format("Successfully set reviewer %s to article %s and version %s.", 
                request.getReviewerId(), articleId, version));
    }
    
    @PostMapping("/deny/{articleId}/{version}")
    public BaseDto<Void> handleDenyArticle(@PathVariable String articleId, @PathVariable Integer version) {
        articleServ.setArticleStatus(articleId, version, ArticleStatus.DENIED);
        
        return new BaseDto<Void>(String.format("Successfully denied article %s and version %s.",
                articleId, version));
    }
    
    @GetMapping("/article/{id}/{version}")
    @Transactional
    public BaseDto<GetEditorArticleDetail> handleGetArticleDetail(@PathVariable String id, @PathVariable Integer version) {
        Article article = articleServ.getArticle(id);
        ArticleVersion articleVersion = article.getVersion(version);
        if (articleVersion == null) {
            throw new ArgumentNotFoundException(String.format("Cannot find specified version %s for article %s!", version, id));
        }
        
        List<EditorArticleReview> editorReviews = new ArrayList<>();
        for (ArticleReview review : articleVersion.getReviews()) {  
            editorReviews.add(EditorArticleReview.builder()
                    .withId(review.getId())
                    .withReviewer(Reviewer.builder()
                            .withUserName(review.getReviewer().getUserName())
                            .build())
                    .withReviewDate(review.getReviewDate())
                    .withStatus(review.getReviewStatus())
                    .withInterest(review.getInterest())
                    .withOriginality(review.getOriginality())
                    .withSpecializationLevel(review.getSpecializationLevel())
                    .withLanguageLevel(review.getLanguageLevel())
                    .withComment(review.getComment())
                    .withAppeal(review.getAppeal())
                    .withAppealDate(review.getAppealDate())
                    .build());
        }
        
        return new BaseDto<GetEditorArticleDetail>(GetEditorArticleDetail.builder()
                .withReviews(editorReviews)
                .build(),
                String.format("Successfully got article with id %s and version %s.", id, version));
    }
    
    @GetMapping("/articles")
    @Transactional
    public BaseDto<GetEditorArticlesResponse> handleGetEditorArticles() {
        return new BaseDto<GetEditorArticlesResponse>(
                GetEditorArticlesResponse.builder()
                        .withArticles(articleServ.getAllArticles().stream()
                                .map(article -> EditorArticle.builder()
                                        .withId(article.getId())
                                        .withName(article.getName())
                                        .withAuthorId(article.getUser().getUserName())
                                        .withVersions(article.getVersions().stream()
                                                .map(articleVersion -> AuthorArticleVersion.builder()
                                                        .withVersion(articleVersion.getVersion())
                                                        .withFileName(articleVersion.getFileName())
                                                        .withPublishDate(articleVersion.getPublishDate())
                                                        .withStatus(articleVersion.getStatus())
                                                        .build())
                                                .collect(Collectors.toList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build(),
                "Successfully got all articles.");
    }
    
    @GetMapping("/reviewers")
    public BaseDto<GetReviewersResponse> handleGetReviewersResponse() {
        return new BaseDto<GetReviewersResponse>(GetReviewersResponse.builder()
                .withReviewers(editorServ.getReviewers().stream().map(user -> Reviewer.builder()
                        .withUserName(user.getUserName())
                        .build())
                        .collect(Collectors.toList()))
                .build(), "Successfully get all reviewers.");
    }
    
    @PostMapping("/accept/{articleId}/{version}")
    public BaseDto<Void> handleSetReviewerToArticle(@PathVariable String articleId, @PathVariable Integer version) {
        articleServ.setArticleStatus(articleId, version, ArticleStatus.ACCEPTED);
        
        return new BaseDto<Void>(String.format("Successfully accepted article %s and version %s.", articleId, version));
    }
    
    @GetMapping("/review/{reviewId}")
    public BaseDto<Void> handleSwitchReviewVisibility(@PathVariable String reviewId, @RequestParam @NotNull boolean visibility) {
        reviewServ.switchAuthorReviewVisibility(reviewId, visibility);
        
        return new BaseDto<Void>("Successfully get all reviewers.");
    }

}

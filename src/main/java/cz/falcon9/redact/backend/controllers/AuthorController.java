package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorAppealRequest;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticle;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleReview;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.author.GetAuthorArticleDetail;
import cz.falcon9.redact.backend.data.dtos.author.GetAuthorArticlesResponse;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.articles.ArticleVersion;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.services.ArticleService;
import cz.falcon9.redact.backend.services.ReviewService;

@RestController
@RequestMapping("/author")
@Secured("ROLE_AUTHOR")
public class AuthorController {

    @Autowired
    private ArticleService articleService;
    
    @Autowired
    private ReviewService reviewServ;
    
    @GetMapping("/articles")
    @Transactional
    public BaseDto<GetAuthorArticlesResponse> handleGetAuthorArticles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        
        return new BaseDto<GetAuthorArticlesResponse>(
                GetAuthorArticlesResponse.builder()
                        .withArticles(articleService.getAllArticles(currentUserName).stream()
                                .map(article -> AuthorArticle.builder()
                                        .withId(article.getId())
                                        .withName(article.getName())
                                        .withVersions(article.getVersions().stream()
                                                .map(articleVersion -> AuthorArticleVersion.builder()
                                                        .withVersion(articleVersion.getVersion())
                                                        .withFileName(articleVersion.getFileName())
                                                        .withPublishDate(articleVersion.getPublishDate())
                                                        .withStatus(articleVersion.getStatus())
                                                        .build())
                                                .collect(Collectors.toList()))
                                        .withEdition(article.getEdition() != null ? article.getEdition().getId() : null)
                                        .build())
                                .collect(Collectors.toList()))
                        .build(),
                String.format("Successfully got articles authored by %s.", currentUserName));
    }
    
    @PostMapping("/article")
    public BaseDto<AuthorArticle> handleCreateArticle(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "edition") String edition,
            @RequestParam(value = "file") MultipartFile file) {
        String[] fileNameParts = file.getOriginalFilename().split("\\.");
        if (fileNameParts.length != 2 || (fileNameParts.length == 2 && !fileNameParts[1].equals("pdf"))) {
            throw new InvalidArgumentException("Only .pdf files are supported!");
        }
        
        Integer editionNumber = Integer.valueOf(edition);
        
        Article article = articleService.insertNewArticle(name, editionNumber == -1 ? null : editionNumber, file);

        return new BaseDto<AuthorArticle>(AuthorArticle.builder()
                .withId(article.getId())
                .withName(article.getName())
                .withVersions(article.getVersions().stream()
                        .map(articleVersion -> AuthorArticleVersion.builder()
                                .withVersion(articleVersion.getVersion())
                                .withFileName(articleVersion.getFileName())
                                .withPublishDate(articleVersion.getPublishDate())
                                .withStatus(articleVersion.getStatus())
                                .build())
                        .collect(Collectors.toList()))
                .withEdition(article.getEdition() != null ? article.getEdition().getId() : null)
                .build(),
                String.format("Successfully created article with id %s.", article.getId()));
    }
    
    @PostMapping("/article/{id}")
    @Transactional
    public BaseDto<AuthorArticle> handleAddArticleVersion(@PathVariable String id,
            @RequestParam(value = "file") MultipartFile file) {
        String[] fileNameParts = file.getOriginalFilename().split("\\.");
        if (fileNameParts.length != 2 || (fileNameParts.length == 2 && !fileNameParts[1].equals("pdf"))) {
            throw new InvalidArgumentException("Only .pdf files are supported!");
        }
        
        Article article = articleService.insertNewArticleVersion(id, file);

        return new BaseDto<AuthorArticle>(AuthorArticle.builder()
                .withId(article.getId())
                .withName(article.getName())
                .withVersions(article.getVersions().stream()
                        .map(articleVersion -> AuthorArticleVersion.builder()
                                .withVersion(articleVersion.getVersion())
                                .withFileName(articleVersion.getFileName())
                                .withPublishDate(articleVersion.getPublishDate())
                                .withStatus(articleVersion.getStatus())
                                .build())
                        .collect(Collectors.toList()))
                .withEdition(article.getEdition() != null ? article.getEdition().getId() : null)
                .build(),
                String.format("Successfully updated article with id %s.", article.getId()));
    }
    
    @GetMapping("/article/{id}/{version}")
    @Transactional
    public BaseDto<GetAuthorArticleDetail> handleGetArticleDetail(@PathVariable String id, @PathVariable Integer version) {
        Article article = articleService.getArticle(id);
        ArticleVersion articleVersion = article.getVersion(version);
        if (articleVersion == null) {
            throw new ArgumentNotFoundException(String.format("Cannot find specified version %s for article %s!", version, id));
        }
        
        List<AuthorArticleReview> authorReviews = new ArrayList<>();
        for (ArticleReview review : articleVersion.getReviews()) {
            if (!review.isVisibleToAuthor() ||
                    review.getReviewStatus() == ArticleReviewStatus.NEW) continue;
            
            authorReviews.add(AuthorArticleReview.builder()
                    .withId(review.getId())
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
        
        return new BaseDto<GetAuthorArticleDetail>(GetAuthorArticleDetail.builder()
                .withName(article.getName())
                .withEdition(article.getEdition() != null ? article.getEdition().getId() : null)
                .withReviews(authorReviews)
                .build(),
                String.format("Successfully got article with id %s and version %s.", id, version));
    }
    
    @PostMapping("/appeal/{reviewId}")
    public BaseDto<Void> handleAppealReview(@PathVariable String reviewId, @Valid @RequestBody AuthorAppealRequest request) {
        reviewServ.appealReview(reviewId, request.getAppeal());
        
        return new BaseDto<Void>(String.format("Successfully appealed review with id %s", reviewId));
    }
    
}

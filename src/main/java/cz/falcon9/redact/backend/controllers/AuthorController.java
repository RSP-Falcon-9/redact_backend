package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticle;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.author.GetArticleDetail;
import cz.falcon9.redact.backend.data.dtos.author.GetAuthorArticlesResponse;
import cz.falcon9.redact.backend.data.models.articles.Article;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.services.ArticleService;

@RestController
@RequestMapping("/author")
@Secured("ROLE_AUTHOR")
public class AuthorController {

    @Autowired
    ArticleService articleService;
    
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
                                                        .build())
                                                .collect(Collectors.toList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build(),
                String.format("Successfully got articles authored by %s.", currentUserName));
    }
    
    @PostMapping("/article")
    public BaseDto<AuthorArticle> handleCreateArcticle(@RequestParam(value = "name") String name,
            @RequestParam(value = "file") MultipartFile file) {
        String[] fileNameParts = file.getOriginalFilename().split("\\.");
        if (fileNameParts.length != 2 || (fileNameParts.length == 2 && !fileNameParts[1].equals("pdf"))) {
            throw new InvalidArgumentException("Only .pdf files are supported!");
        }
        
        Article article = articleService.insertNewArticle(name, file);

        return new BaseDto<AuthorArticle>(AuthorArticle.builder()
                .withId(article.getId())
                .withName(article.getName())
                .withVersions(article.getVersions().stream()
                        .map(articleVersion -> AuthorArticleVersion.builder()
                                .withVersion(articleVersion.getVersion())
                                .withFileName(articleVersion.getFileName())
                                .withPublishDate(articleVersion.getPublishDate())
                                .build())
                        .collect(Collectors.toList()))
                .build(),
                String.format("Successfully created article with id %s.", article.getId()));
    }
    
    @GetMapping("/article/{id}/{version}")
    public BaseDto<GetArticleDetail> handleGetArticleDetail(@PathVariable String id, @PathVariable Integer version) {
        Article article = articleService.getArticle(id);
        
        return new BaseDto<GetArticleDetail>(GetArticleDetail.builder()
                .withName(article.getName())
                .build(),
                String.format("Successfully got article with id %s and version %s.", id, version));
    }
    
}

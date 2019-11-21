package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticle;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.author.GetAuthorArticlesResponse;
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
                                        .withName(article.getName())
                                        .withVersions(article.getVersions().stream()
                                                .map(articleVersion -> AuthorArticleVersion.builder()
                                                        .withArticleId(articleVersion.getArticleId())
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
    
}

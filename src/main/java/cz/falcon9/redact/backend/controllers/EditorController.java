package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.editor.EditorArticle;
import cz.falcon9.redact.backend.data.dtos.editor.GetEditorArticlesResponse;
import cz.falcon9.redact.backend.services.ArticleService;

@Service
@Secured("ROLE_EDITOR")
public class EditorController {

    @Autowired
    ArticleService articleService;
    
    @GetMapping("/articles")
    @Transactional
    public BaseDto<GetEditorArticlesResponse> handleGetEditorArticles() {
        return new BaseDto<GetEditorArticlesResponse>(
                GetEditorArticlesResponse.builder()
                        .withArticles(articleService.getAllArticles().stream()
                                .map(article -> EditorArticle.builder()
                                        .withId(article.getId())
                                        .withName(article.getName())
                                        .withAuthorId(article.getUser().getUserName())
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
                "Successfully got all articles.");
    }
    
}

package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import cz.falcon9.redact.backend.data.dtos.editor.EditorArticle;
import cz.falcon9.redact.backend.data.dtos.editor.GetEditorArticlesResponse;
import cz.falcon9.redact.backend.data.dtos.editor.GetReviewersResponse;
import cz.falcon9.redact.backend.data.dtos.editor.Reviewer;
import cz.falcon9.redact.backend.services.ArticleService;
import cz.falcon9.redact.backend.services.EditorService;

@RestController
@RequestMapping("/editor")
@Secured("ROLE_EDITOR")
public class EditorController {

    @Autowired
    ArticleService articleService;
    
    @Autowired
    EditorService editorServ;
    
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
                                                        .withStatus(articleVersion.getStatus())
                                                        .build())
                                                .collect(Collectors.toList()))
                                        .build())
                                .collect(Collectors.toList()))
                        .build(),
                "Successfully got all articles.");
    }
    
    /*@PostMapping("/deny/{articleId}/{version}")
    public BaseDto<Void> handleDenyArticle(@PathVariable String articleId, @PathVariable Integer version, @RequestParam("reason") String reason) {
        
        
        return new BaseDto<Void>(String.format("Successfully denied article %s and version %s with reason %s.", articleId, version, reason));
    }*/
    
    @GetMapping("/reviewers")
    public BaseDto<GetReviewersResponse> handleGetReviewersResponse() {
        return new BaseDto<GetReviewersResponse>(GetReviewersResponse.builder()
                .withReviewers(editorServ.getReviewers().stream().map(user -> Reviewer.builder()
                        .withUserName(user.getUserName())
                        .build())
                        .collect(Collectors.toList()))
                .build(), "Successfully get all reviewers.");
    }
    
    @PostMapping("/review/{articleId}/{version}")
    public BaseDto<Void> handleSetReviewerToArticle(@PathVariable String articleId, @PathVariable Integer version, @RequestParam("reviewerId") String reviewerId) {
        editorServ.assignReviewerToArticle(articleId, version, reviewerId);
        
        return new BaseDto<Void>(String.format("Successfully set reviewer %s to article %s and version %s.", reviewerId, articleId, version));
    }
    
    /*@PostMapping("/accept/{articleId}/{version}")
    public BaseDto<Void> handleSetReviewerToArticle(@PathVariable String articleId, @PathVariable Integer version) {
        
        
        return new BaseDto<Void>(String.format("Successfully accepted article %s and version %s.", articleId, version));
    }*/

}

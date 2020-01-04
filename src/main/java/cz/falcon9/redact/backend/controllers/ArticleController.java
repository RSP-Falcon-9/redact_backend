package cz.falcon9.redact.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.services.ArticleService;

@RestController
@RequestMapping("/article")
@Secured({ "ROLE_AUTHOR", "ROLE_EDITOR", "ROLE_REVIEWER", "ROLE_CHIEF_EDITOR" })
public class ArticleController {
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping(value = "/{id}/{version}/file", produces = MediaType.APPLICATION_PDF_VALUE )
    public FileSystemResource handleGetArticleFile(@PathVariable String id, @PathVariable Integer version) {
        return articleService.getArticleFile(id, version);
    }
    
}

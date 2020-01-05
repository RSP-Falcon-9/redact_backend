package cz.falcon9.redact.backend.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.Edition;
import cz.falcon9.redact.backend.data.dtos.chiefeditor.CreateEditionRequest;
import cz.falcon9.redact.backend.data.dtos.chiefeditor.CreateEditionResponse;
import cz.falcon9.redact.backend.data.models.editions.EditionEntity;
import cz.falcon9.redact.backend.services.ArticleService;
import cz.falcon9.redact.backend.services.EditionService;

@RestController
@RequestMapping("/chief-editor")
@Secured("ROLE_CHIEF_EDITOR")
public class ChiefEditorController {

    @Autowired
    private ArticleService articleServ;
    
    @Autowired
    private EditionService editionServ;
    
    @DeleteMapping("/article/{articleId}/{version}")
    public BaseDto<Void> handleDeleteArticle(@PathVariable String articleId,
            @PathVariable Integer version) {
        articleServ.removeArticle(articleId, version);
        
        return new BaseDto<Void>(String.format("Article %s with version %s deleted successfully!", articleId, version));
    }
    
    @DeleteMapping("/article/{articleId}")
    public BaseDto<Void> handleDeleteArticle(@PathVariable String articleId) {
        articleServ.removeArticle(articleId);
        
        return new BaseDto<Void>(String.format("Article %s deleted successfully!", articleId));
    }
    
    @PostMapping("/edition")
    public BaseDto<CreateEditionResponse> createEdition(@RequestBody @Valid CreateEditionRequest request) {
        EditionEntity editionEntity = editionServ.createNew(request.getDescription(), request.getDeadline());
        Edition edition = Edition.builder()
                .withId(editionEntity.getId())
                .withDescription(editionEntity.getDescription())
                .withDeadline(editionEntity.getDeadline())
                .withArchived(editionEntity.isArchived())
                .build();
        
        return new BaseDto<CreateEditionResponse>(
                CreateEditionResponse.builder()
                .withEdition(edition)
                .build(),
                String.format("Edition %s was successfully created!", edition.getId()));
    }
    
    @DeleteMapping("/edition/{editionNumber}")
    public BaseDto<Void> deleteEdition(@PathVariable Integer editionNumber) {
        editionServ.delete(editionNumber);
        
        return new BaseDto<Void>(String.format("Edition %s was successfully deleted!", editionNumber));
    }
    
    @GetMapping("/edition/archive/{editionNumber}")
    public BaseDto<Void> archiveEdition(@PathVariable Integer editionNumber) {
        editionServ.archive(editionNumber);
        
        return new BaseDto<Void>(String.format("Edition %s was successfully archived!", editionNumber));
    }
    
}

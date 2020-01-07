package cz.falcon9.redact.backend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.Edition;
import cz.falcon9.redact.backend.data.dtos.GetEditionsResponse;
import cz.falcon9.redact.backend.data.dtos.unauthenticated.GetArchivesResponse;
import cz.falcon9.redact.backend.services.EditionService;

@RestController
@RequestMapping("/unauthenticated")
public class UnauthenticatedController {

    @Autowired
    private EditionService editionServ;
    
    @GetMapping("/archives")
    public BaseDto<GetArchivesResponse> handleGetArchives() {
        List<String> archives = editionServ.getArchivedEditions().stream()
                .map(archivedEdition -> archivedEdition.getId().toString())
                .collect(Collectors.toList());
        
        return new BaseDto<GetArchivesResponse>(
                GetArchivesResponse.builder()
                .withArchives(archives)
                .build(),
                "Successfully got list of archives.");
    }
    
    @GetMapping(value = "/archive/{editionNumber}", produces="application/zip")
    public FileSystemResource handleGetArchiveFile(@PathVariable Integer editionNumber) {
        return editionServ.getArchiveFile(editionNumber);
    }
    
    @GetMapping("/editions")
    public BaseDto<GetEditionsResponse> handleGetEditions() {
        List<Edition> editions = editionServ.getEditions().stream()
                .map(editionEntity ->Edition.builder()
                        .withId(editionEntity.getId())
                        .withDescription(editionEntity.getDescription())
                        .withDeadline(editionEntity.getDeadline())
                        .withArchived(editionEntity.isArchived())
                        .build())
                .collect(Collectors.toList());
        
        return new BaseDto<GetEditionsResponse>(
                GetEditionsResponse.builder()
                .withEditions(editions)
                .build(),
                "Successfully got editions!");
    }
    
}

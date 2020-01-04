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
import cz.falcon9.redact.backend.services.EditionService;

@RestController
@RequestMapping("/unauthorized")
public class UnauthorizedController {

    @Autowired
    private EditionService editionServ;
    
    @GetMapping(value = "/archives")
    public BaseDto<List<String>> handleGetArchives() {
        return new BaseDto<List<String>>(
                editionServ.getArchivedEditions().stream()
                .map(archivedEdition -> archivedEdition.getNumber().toString())
                .collect(Collectors.toList()),
                "Successfully got list of archives.");
    }
    
    @GetMapping(value = "/archive/{editionNumber}")
    public FileSystemResource handleGetArchiveFile(@PathVariable Integer editionNumber) {
        return editionServ.getArchiveFile(editionNumber);
    }
    
}

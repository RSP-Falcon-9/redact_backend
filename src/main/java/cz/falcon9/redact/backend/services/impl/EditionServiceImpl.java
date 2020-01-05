package cz.falcon9.redact.backend.services.impl;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.models.editions.EditionEntity;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.providers.RedactConfigProvider;
import cz.falcon9.redact.backend.repositories.EditionRepository;
import cz.falcon9.redact.backend.services.EditionService;

@Service
public class EditionServiceImpl implements EditionService {

    @Autowired
    private RedactConfigProvider config;
    
    @Autowired
    private EditionRepository editionRepo;
    
    
    @Override
    public List<EditionEntity> getEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAll().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public List<EditionEntity> getUnarchivedEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAllUnarchived().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public List<EditionEntity> getArchivedEditions() {
        List<EditionEntity> editions = new ArrayList<>();
        
        editionRepo.findAllArchived().forEach(editions::add);;
        
        return editions;
    }
    
    @Override
    public FileSystemResource getArchiveFile(Integer number) {
        File articleFile = new File(config.getArchiveZipFilePath(number));
        if (!articleFile.exists()) {
            throw new ArgumentNotFoundException("File for given archive doesn't exist.");
        }

        return new FileSystemResource(articleFile);
    }
    
    @Override
    public EditionEntity getEdition(Integer number) {
        return editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
    }
    
    @Override
    public EditionEntity createNew(String description, ZonedDateTime deadline) {
        return editionRepo.save(
                EditionEntity.builder()
                .withDescription(description)
                .withDeadline(deadline)
                .withArchived(false)
                .build());
    }

    @Override
    public EditionEntity edit(Integer number, String description, ZonedDateTime deadline) {
        EditionEntity edition = editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
        
        return editionRepo.save(
                EditionEntity.builder()
                .withId(edition.getId())
                .withDescription(description)
                .withDeadline(deadline)
                .withArchived(edition.isArchived())
                .build());
    }

    @Override
    public void archive(Integer number) {
        EditionEntity edition = editionRepo.findById(number).orElseThrow(() ->
            new InvalidArgumentException(String.format("Edition %s is not valid!", number))
        );
        
        if (edition.isArchived()) return;
        
        editionRepo.save(
                EditionEntity.builder()
                .withId(edition.getId())
                .withDescription(edition.getDescription())
                .withDeadline(edition.getDeadline())
                .withArchived(true)
                .build());
    }

    @Override
    public void delete(Integer number) {
        editionRepo.deleteById(number);
    }
    
}

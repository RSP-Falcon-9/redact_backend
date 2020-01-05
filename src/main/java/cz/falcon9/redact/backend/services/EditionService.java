package cz.falcon9.redact.backend.services;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

import cz.falcon9.redact.backend.data.models.editions.EditionEntity;

public interface EditionService {
    
    List<EditionEntity> getEditions();
    List<EditionEntity> getUnarchivedEditions();
    List<EditionEntity> getArchivedEditions();
    FileSystemResource getArchiveFile(Integer number);
    EditionEntity getEdition(Integer number);
    EditionEntity createNew(String description, ZonedDateTime deadline);
    EditionEntity edit(Integer number, String description, ZonedDateTime deadline);
    void archive(Integer number);
    void delete(Integer number);
    
}

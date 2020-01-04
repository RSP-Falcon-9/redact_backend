package cz.falcon9.redact.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import cz.falcon9.redact.backend.data.models.editions.EditionEntity;

public interface EditionRepository extends CrudRepository<EditionEntity, Integer> {
    
    @Query("SELECT e FROM EditionEntity e WHERE e.archived = true")
    List<EditionEntity> findAllArchived();
    
}

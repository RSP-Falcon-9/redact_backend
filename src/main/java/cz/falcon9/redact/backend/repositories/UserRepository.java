package cz.falcon9.redact.backend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cz.falcon9.redact.backend.data.models.auth.User;

public interface UserRepository extends CrudRepository<User, String> {
    
    List<User> findAll();
    
}

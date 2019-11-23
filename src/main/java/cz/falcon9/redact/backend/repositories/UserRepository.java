package cz.falcon9.redact.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cz.falcon9.redact.backend.data.models.auth.User;

public interface UserRepository extends CrudRepository<User, String> {
    
    List<User> findAll();
    
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.role = :role")
    List<User> findByRole(@Param("role") String role);
    
}

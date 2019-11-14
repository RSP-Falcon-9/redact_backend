package cz.falcon9.redact.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.models.User;
import cz.falcon9.redact.backend.repositories.UserRepository;

@Service
@Secured("ROLE_ADMIN")
public class AdminService {

    @Autowired
    UserRepository userRepository;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public void insertUser(User user) {
    	if (user.getUserName().isEmpty()) {
    		throw new IllegalArgumentException();
    	}
    	
    	userRepository.save(user);
    }
    
}

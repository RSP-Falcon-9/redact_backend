package cz.falcon9.redact.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.models.User;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.repositories.UserRepository;

@Service
@Secured("ROLE_ADMIN")
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User getUser(String userName) {
        Optional<User> optionalUser = userRepository.findById(userName);
        
        if (!optionalUser.isPresent()) {
            throw new ArgumentNotFoundException("User with given name does not exists!"); 
        }
        
        return optionalUser.get();
    }
    
    @Override
    public void insertUser(User user) {
    	if (user.getUserName().isEmpty()) {
    		throw new InvalidArgumentException("Cannot insert user with blank name!");
    	}
    	
        /*if (userRepository.existsById(user.getUserName())) {
            throw new InvalidArgumentException(String.format("User %s already exists!", user.getUserName()));
        }*/
    	
    	userRepository.save(user);
    }
    
    @Override
    public void deleteUser(String userName) {
        if (userName.isEmpty()) {
            throw new InvalidArgumentException("UserId is empty!");
        }
        
        userRepository.deleteById(userName);
    }
    
}

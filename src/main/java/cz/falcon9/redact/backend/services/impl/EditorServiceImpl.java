package cz.falcon9.redact.backend.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.repositories.UserRepository;
import cz.falcon9.redact.backend.services.EditorService;

@Service
public class EditorServiceImpl implements EditorService {

    private final Logger log = LoggerFactory.getLogger(EditorServiceImpl.class);
    
    @Autowired
    UserRepository userRepo;
    
    @Override
    public List<User> getReviewers() {
        return userRepo.findByRole("ROLE_REVIEWER");
    }

}

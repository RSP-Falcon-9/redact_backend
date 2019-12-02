package cz.falcon9.redact.backend.services;

import java.util.List;

import cz.falcon9.redact.backend.data.models.auth.User;

public interface EditorService {

    List<User> getReviewers();
    
}

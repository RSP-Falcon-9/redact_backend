package cz.falcon9.redact.backend.services;

import java.util.List;

import cz.falcon9.redact.backend.data.models.auth.User;

public interface AdminService {

    List<User> getAllUsers();
    
    User getUser(String userName);

    void insertUser(User user);

    void deleteUser(String userName);

}

package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.admin.GetUsersResponse;
import cz.falcon9.redact.backend.data.dtos.admin.UserCreationRequest;
import cz.falcon9.redact.backend.data.dtos.admin.UserDetail;
import cz.falcon9.redact.backend.data.models.User;
import cz.falcon9.redact.backend.data.models.UserRole;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.services.AdminService;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    
    @Autowired
    AdminService adminService;
    
    @GetMapping("/users")
    @Transactional(readOnly = true)
    public GetUsersResponse handleGetUsers() {
        return GetUsersResponse.builder()
                .withUsers(adminService.getAllUsers().stream()
                        .map(user -> UserDetail.builder().withUserName(user.getUserName())
                                .withRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
    
    @GetMapping("/user/{id}")
    public UserDetail handleGetUserDetails(@PathVariable String userName) {
        User user = adminService.getUser(userName);
        
        return UserDetail.builder()
                .withUserName(user.getUserName())
                .withRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList()))
                .build();
    }
    
    @PostMapping("/user/{id}")
    public void handleCreateUser(@RequestBody @Valid UserCreationRequest request) {
        adminService.insertUser(User.builder()
                .withUserName(request.getUserName())
                .withPassword(request.getPassword())
                .build());
    }
    
    @DeleteMapping("/user/{id}")
    public void handleDeleteUser(@PathVariable String userName) {
    	// Prevent admin cannot delete itself.
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    	    String currentUserName = authentication.getName();
    	    
    	    if (currentUserName == userName) {
    	    	throw new InvalidArgumentException("Admin cannot delete himself!");
    	    }
    	}
    	
    	adminService.deleteUser(userName);
    }

}

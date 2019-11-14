package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.admin.GetUsersResponse;
import cz.falcon9.redact.backend.data.dtos.admin.UserCreationRequest;
import cz.falcon9.redact.backend.data.dtos.admin.UserDto;
import cz.falcon9.redact.backend.data.models.User;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.services.AdminService;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    
    @Autowired
    AdminService adminService;
    
    @GetMapping("/users")
    public GetUsersResponse handleGetUsers() {
        List<User> users = adminService.getAllUsers();
        List<UserDto> dtoUsers = new ArrayList<>();
        
        for (User user : users) {
            dtoUsers.add(UserDto.builder().withUserName(user.getUserName()).build());
        }
        
        return GetUsersResponse.builder().withUsers(dtoUsers).build();
    }
    
    @GetMapping("/user/{id}")
    public String handleGetUserDetails(@PathVariable String userName) {
        return "ITS OKAY MAJ BRUDA2";
    }
    
    @PostMapping("/user/{id}")
    public void handleCreateUser(@RequestBody @Valid UserCreationRequest request) {
    	
    }
    
    @DeleteMapping("/user/{id}")
    public void handleDeleteUser(@PathVariable String userName) throws MethodArgumentNotValidException {
    	// Prevent admin cannot delete itself.
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    	    String currentUserName = authentication.getName();
    	    
    	    if (currentUserName == userName) {
    	    	throw new InvalidArgumentException("Admin cannot delete himself!");
    	    }
    	}
    	
    	//adminService.re
    }

}

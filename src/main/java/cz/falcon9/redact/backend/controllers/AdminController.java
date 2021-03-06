package cz.falcon9.redact.backend.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;
import cz.falcon9.redact.backend.data.dtos.admin.GetUsersResponse;
import cz.falcon9.redact.backend.data.dtos.admin.UserCreationRequest;
import cz.falcon9.redact.backend.data.dtos.admin.UserDetail;
import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.data.models.auth.UserRole;
import cz.falcon9.redact.backend.services.AdminService;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {
    
    @Autowired
    AdminService adminService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/users")
    @Transactional(readOnly = true)
    public BaseDto<GetUsersResponse> handleGetUsers() {
        return new BaseDto<GetUsersResponse>(GetUsersResponse.builder()
                .withUsers(adminService.getAllUsers().stream()
                        .map(user -> UserDetail.builder().withUserName(user.getUserName())
                                .withRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList()))
                                .build())
                        .collect(Collectors.toList()))
                .build(), "Successfully fetched list!");
    }
    
    /*@GetMapping("/user/{id}")
    public UserDetail handleGetUserDetails(@PathVariable String userName) {
        User user = adminService.getUser(userName);
        
        return UserDetail.builder()
                .withUserName(user.getUserName())
                .withRoles(user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList()))
                .build();
    }*/
    
    @PostMapping("/user/{userName}")
    public BaseDto<Void> handleCreateUser(@PathVariable String userName, @RequestBody @Valid UserCreationRequest request) {
        adminService.insertUser(User.builder()
                .withUserName(userName)
                .withPassword(passwordEncoder.encode(request.getPassword()))
                .withRoles(request.getRoles().stream()
                        .map(role -> UserRole.builder()
                                .withUsername(userName)
                                .withRole(role)
                                .build())
                        .collect(Collectors.toList()))
                .build());
        
        return new BaseDto<Void>(String.format("Successfully created new user %s.", userName));
    }

    @DeleteMapping("/user/{userName}")
    public BaseDto<Void> handleDeleteUser(@PathVariable String userName) {
    	adminService.deleteUser(userName);
    	
    	return new BaseDto<Void>(String.format("Successfully deleted user %s.", userName));
    }

}

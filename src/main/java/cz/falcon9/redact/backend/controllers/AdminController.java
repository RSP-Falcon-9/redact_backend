package cz.falcon9.redact.backend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.daos.User;
import cz.falcon9.redact.backend.data.dtos.admin.GetUsersResponse;
import cz.falcon9.redact.backend.data.dtos.admin.ImmutableGetUsersResponse;
import cz.falcon9.redact.backend.data.dtos.admin.ImmutableUserDto;
import cz.falcon9.redact.backend.data.dtos.admin.UserDto;
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
            dtoUsers.add(ImmutableUserDto.builder().userName(user.getUserName()).build());
        }
        
        return ImmutableGetUsersResponse.builder().addAllUsers(dtoUsers).build();
    }
    
    @GetMapping("/user/{id}")
    public String handleGetUserDetails(@PathVariable String userId) {
        return "ITS OKAY MAJ BRUDA2";
    }
    
    @PostMapping("/user/{id}")
    public String handleCreateUser(@PathVariable String userId) {
        return "ITS OKAY MAJ BRUDA2";
    }
    
    @DeleteMapping("/user/{id}")
    public String handleDeleteUser(@PathVariable String userId) {
        return "ITS OKAY MAJ BRUDA2";
    }

}

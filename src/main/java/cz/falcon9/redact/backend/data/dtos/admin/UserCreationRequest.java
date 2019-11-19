package cz.falcon9.redact.backend.data.dtos.admin;

import java.util.List;

import javax.validation.constraints.NotNull;

public class UserCreationRequest {

    @NotNull
    private String password;
    
    private List<String> roles;
    
    public String getPassword() {
        return password;
    }
    
    public List<String> getRoles() {
        return roles;
    }

}

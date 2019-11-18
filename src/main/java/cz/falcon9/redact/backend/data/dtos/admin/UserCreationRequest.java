package cz.falcon9.redact.backend.data.dtos.admin;

import javax.validation.constraints.NotNull;

public class UserCreationRequest {

    @NotNull
    private String password;
    
    public String getPassword() {
        return password;
    }

}

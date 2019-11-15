package cz.falcon9.redact.backend.data.dtos.admin;

import javax.validation.constraints.NotNull;

public class UserCreationRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }
    
    public String getPassword() {
        return password;
    }

}

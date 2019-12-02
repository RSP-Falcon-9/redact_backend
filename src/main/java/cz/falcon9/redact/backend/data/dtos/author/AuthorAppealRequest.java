package cz.falcon9.redact.backend.data.dtos.author;

import javax.validation.constraints.NotEmpty;

public class AuthorAppealRequest {

    @NotEmpty
    private String appeal;

    public String getAppeal() {
        return appeal;
    }
    
}

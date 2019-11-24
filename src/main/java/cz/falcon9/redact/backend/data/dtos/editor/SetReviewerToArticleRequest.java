package cz.falcon9.redact.backend.data.dtos.editor;

import javax.validation.constraints.NotEmpty;

public class SetReviewerToArticleRequest {

    @NotEmpty
    private String reviewerId;

    public String getReviewerId() {
        return reviewerId;
    }
    
}

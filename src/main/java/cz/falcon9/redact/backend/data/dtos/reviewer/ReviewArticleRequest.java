package cz.falcon9.redact.backend.data.dtos.reviewer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewArticleRequest {
    
    @NotNull
    private int interest;

    @NotNull
    private int originality;

    @NotNull
    private int specializationLevel;

    @NotNull
    private int languageLevel;

    @NotEmpty
    private String comment;

    public String getComment() {
        return comment;
    }
    
    public int getInterest() {
        return interest;
    }
    
    public int getLanguageLevel() {
        return languageLevel;
    }
    
    public int getOriginality() {
        return originality;
    }
    
    public int getSpecializationLevel() {
        return specializationLevel;
    }

}

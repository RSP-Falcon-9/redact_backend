package cz.falcon9.redact.backend.data.dtos.reviewer;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewArticleRequest {
    
    @NotNull
    @Min(1)
    @Max(5)
    private int interest;

    @NotNull
    @Min(1)
    @Max(5)
    private int originality;

    @NotNull
    @Min(1)
    @Max(5)
    private int specializationLevel;

    @NotNull
    @Min(1)
    @Max(5)
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

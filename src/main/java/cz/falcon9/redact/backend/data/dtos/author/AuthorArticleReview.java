package cz.falcon9.redact.backend.data.dtos.author;

import javax.annotation.Generated;

public class AuthorArticleReview {

    /**
     * Builder to build {@link AuthorArticleReview}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private int interest;
        private int originality;
        private int specializationLevel;
        private int languageLevel;
        private String comment;

        private Builder() {
        }

        public AuthorArticleReview build() {
            return new AuthorArticleReview(this);
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withInterest(int interest) {
            this.interest = interest;
            return this;
        }

        public Builder withLanguageLevel(int languageLevel) {
            this.languageLevel = languageLevel;
            return this;
        }

        public Builder withOriginality(int originality) {
            this.originality = originality;
            return this;
        }

        public Builder withSpecializationLevel(int specializationLevel) {
            this.specializationLevel = specializationLevel;
            return this;
        }
    }

    /**
     * Creates builder to build {@link AuthorArticleReview}.
     * 
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String id;
    
    private int interest;
    
    private int originality;

    private int specializationLevel;
    
    private int languageLevel;

    private String comment;

    @Generated("SparkTools")
    private AuthorArticleReview(Builder builder) {
        this.id = builder.id;
        this.interest = builder.interest;
        this.originality = builder.originality;
        this.specializationLevel = builder.specializationLevel;
        this.languageLevel = builder.languageLevel;
        this.comment = builder.comment;
    }

    public String getComment() {
        return comment;
    }

    public String getId() {
        return id;
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

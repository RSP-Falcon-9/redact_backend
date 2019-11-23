package cz.falcon9.redact.backend.data.articles;

import javax.annotation.Generated;

public class ArticleReviewForm {

    /**
     * Builder to build {@link ArticleReviewForm}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private int interest;
        private int originality;
        private int specializationLevel;
        private int languageLevel;
        private String comment;

        private Builder() {
        }

        public ArticleReviewForm build() {
            return new ArticleReviewForm(this);
        }

        public Builder withComment(String comment) {
            this.comment = comment;
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
     * Creates builder to build {@link ArticleReviewForm}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    private int interest;
    private int originality;
    private int specializationLevel;
    private int languageLevel;
    private String comment;
    @Generated("SparkTools")
    private ArticleReviewForm(Builder builder) {
        this.interest = builder.interest;
        this.originality = builder.originality;
        this.specializationLevel = builder.specializationLevel;
        this.languageLevel = builder.languageLevel;
        this.comment = builder.comment;
    }
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

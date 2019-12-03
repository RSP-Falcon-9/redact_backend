package cz.falcon9.redact.backend.data.dtos.editor;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;

import java.sql.Date;

import javax.annotation.Generated;

public class EditorArticleReview {
    
    /**
     * Builder to build {@link EditorArticleReview}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private Reviewer reviewer;
        private Date reviewDate;
        private ArticleReviewStatus status;
        private int interest;
        private int originality;
        private int specializationLevel;
        private int languageLevel;
        private String comment;
        private String appeal;
        private Date appealDate;

        private Builder() {
        }

        public EditorArticleReview build() {
            return new EditorArticleReview(this);
        }

        public Builder withAppeal(String appeal) {
            this.appeal = appeal;
            return this;
        }

        public Builder withAppealDate(Date appealDate) {
            this.appealDate = appealDate;
            return this;
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

        public Builder withReviewDate(Date reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder withReviewer(Reviewer reviewer) {
            this.reviewer = reviewer;
            return this;
        }

        public Builder withSpecializationLevel(int specializationLevel) {
            this.specializationLevel = specializationLevel;
            return this;
        }

        public Builder withStatus(ArticleReviewStatus status) {
            this.status = status;
            return this;
        }
    }

    /**
     * Creates builder to build {@link EditorArticleReview}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String id;

    private Reviewer reviewer;
    
    private Date reviewDate;

    private ArticleReviewStatus status;

    private int interest;

    private int originality;

    private int specializationLevel;

    private int languageLevel;

    private String comment;
    
    private String appeal;
    
    private Date appealDate;

    @Generated("SparkTools")
    private EditorArticleReview(Builder builder) {
        this.id = builder.id;
        this.reviewer = builder.reviewer;
        this.reviewDate = builder.reviewDate;
        this.status = builder.status;
        this.interest = builder.interest;
        this.originality = builder.originality;
        this.specializationLevel = builder.specializationLevel;
        this.languageLevel = builder.languageLevel;
        this.comment = builder.comment;
        this.appeal = builder.appeal;
        this.appealDate = builder.appealDate;
    }

    public String getAppeal() {
        return appeal;
    }

    public Date getAppealDate() {
        return appealDate;
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

    public Date getReviewDate() {
        return reviewDate;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public int getSpecializationLevel() {
        return specializationLevel;
    }

    public ArticleReviewStatus getStatus() {
        return status;
    }
    
}

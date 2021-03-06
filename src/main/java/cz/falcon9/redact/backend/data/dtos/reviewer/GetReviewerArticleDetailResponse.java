package cz.falcon9.redact.backend.data.dtos.reviewer;

import java.sql.Date;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import javax.annotation.Generated;

public class GetReviewerArticleDetailResponse {
    
    /**
     * Builder to build {@link GetReviewerArticleDetailResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String name;
        private String reviewId;
        private Date reviewDate;
        private ArticleReviewStatus reviewStatus;
        private int interest;
        private int originality;
        private int specializationLevel;
        private int languageLevel;
        private String comment;
        private String appeal;
        private Date appealDate;

        private Builder() {
        }

        public GetReviewerArticleDetailResponse build() {
            return new GetReviewerArticleDetailResponse(this);
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

        public Builder withInterest(int interest) {
            this.interest = interest;
            return this;
        }

        public Builder withLanguageLevel(int languageLevel) {
            this.languageLevel = languageLevel;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
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

        public Builder withReviewId(String reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder withReviewStatus(ArticleReviewStatus reviewStatus) {
            this.reviewStatus = reviewStatus;
            return this;
        }

        public Builder withSpecializationLevel(int specializationLevel) {
            this.specializationLevel = specializationLevel;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetReviewerArticleDetailResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    private String name;

    private String reviewId;

    private Date reviewDate;

    private ArticleReviewStatus reviewStatus;

    private int interest;

    private int originality;

    private int specializationLevel;

    private int languageLevel;
    
    private String comment;

    private String appeal;
    
    private Date appealDate;

    @Generated("SparkTools")
    private GetReviewerArticleDetailResponse(Builder builder) {
        this.name = builder.name;
        this.reviewId = builder.reviewId;
        this.reviewDate = builder.reviewDate;
        this.reviewStatus = builder.reviewStatus;
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

    public int getInterest() {
        return interest;
    }

    public int getLanguageLevel() {
        return languageLevel;
    }

    public String getName() {
        return name;
    }

    public int getOriginality() {
        return originality;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public ArticleReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public int getSpecializationLevel() {
        return specializationLevel;
    }
    
}

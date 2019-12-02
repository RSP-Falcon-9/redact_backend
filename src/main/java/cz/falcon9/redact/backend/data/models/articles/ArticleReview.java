package cz.falcon9.redact.backend.data.models.articles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.models.auth.User;
import javax.annotation.Generated;

@Entity
@Table(name = "redact_article_review")
public class ArticleReview {

    /**
     * Builder to build {@link ArticleReview}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private String articleId;
        private int version;
        private ArticleReviewStatus reviewStatus;
        private User reviewer;
        private int interest;
        private int originality;
        private int specializationLevel;
        private int languageLevel;
        private String comment;
        private Date reviewDate;
        private boolean visibleToAuthor;
        private String appeal;
        private Date appealDate;

        private Builder() {
        }

        public ArticleReview build() {
            return new ArticleReview(this);
        }

        public Builder withAppeal(String appeal) {
            this.appeal = appeal;
            return this;
        }

        public Builder withAppealDate(Date appealDate) {
            this.appealDate = appealDate;
            return this;
        }

        public Builder withArticleId(String articleId) {
            this.articleId = articleId;
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

        public Builder withReviewer(User reviewer) {
            this.reviewer = reviewer;
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

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder withVisibleToAuthor(boolean visibleToAuthor) {
            this.visibleToAuthor = visibleToAuthor;
            return this;
        }
    }

    /**
     * Creates builder to build {@link ArticleReview}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Id
    @Column()
    private String id;

    @Column(name = "article_id")
    private String articleId;

    @Column(columnDefinition = "TINYINT")
    private int version;

    @Column(name="review_status", columnDefinition = "TINYINT")
    private ArticleReviewStatus reviewStatus;

    @ManyToOne(optional = true)
    @JoinColumn(name = "reviewer_id", nullable = true)
    private User reviewer;

    @Column(columnDefinition = "TINYINT")
    private int interest;

    @Column(columnDefinition = "TINYINT")
    private int originality;

    @Column(name = "specialization_level", columnDefinition = "TINYINT")
    private int specializationLevel;

    @Column(name = "language_level", columnDefinition = "TINYINT")
    private int languageLevel;

    @Column
    private String comment;

    @Column(name = "review_date")
    private Date reviewDate;

    @Column(name = "visible_to_author")
    private boolean visibleToAuthor;

    @Column
    private String appeal;

    @Column(name = "appeal_date")
    private Date appealDate;

    private ArticleReview() { }
    
    @Generated("SparkTools")
    private ArticleReview(Builder builder) {
        this.id = builder.id;
        this.articleId = builder.articleId;
        this.version = builder.version;
        this.reviewStatus = builder.reviewStatus;
        this.reviewer = builder.reviewer;
        this.interest = builder.interest;
        this.originality = builder.originality;
        this.specializationLevel = builder.specializationLevel;
        this.languageLevel = builder.languageLevel;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
        this.visibleToAuthor = builder.visibleToAuthor;
        this.appeal = builder.appeal;
        this.appealDate = builder.appealDate;
    }
    
    public String getAppeal() {
        return appeal;
    }

    public Date getAppealDate() {
        return appealDate;
    }
    
    public String getArticleId() {
        return articleId;
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

    public User getReviewer() {
        return reviewer;
    }

    public ArticleReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public int getSpecializationLevel() {
        return specializationLevel;
    }

    public int getVersion() {
        return version;
    }

    public boolean isVisibleToAuthor() {
        return visibleToAuthor;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }
    
    public void setAppealDate(Date appealDate) {
        this.appealDate = appealDate;
    }

    public void setReviewStatus(ArticleReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public void setVisibleToAuthor(boolean visibleToAuthor) {
        this.visibleToAuthor = visibleToAuthor;
    }
    
}

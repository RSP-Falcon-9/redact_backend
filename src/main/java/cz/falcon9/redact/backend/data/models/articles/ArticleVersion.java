package cz.falcon9.redact.backend.data.models.articles;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;

import javax.annotation.Generated;
import java.util.Collections;

@Entity
@Table(name = "redact_arcticle_version")
@IdClass(ArticleVersionCompositeId.class)
public class ArticleVersion {
    
    @Id
    @Column(name = "article_id")
    private String articleId;

    @Id
    @Column(columnDefinition = "TINYINT")
    private int version;
    
    @Column(name = "file_name", nullable = false)
    private String fileName;
    
    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    @Column(columnDefinition = "TINYINT")
    private ArticleStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(
            name = "article_id",
            referencedColumnName = "article_id"),
        @JoinColumn(
            name = "version",
            referencedColumnName = "version")
    })
    private List<ArticleReview> reviews;

    @Generated("SparkTools")
    private ArticleVersion(Builder builder) {
        this.articleId = builder.articleId;
        this.version = builder.version;
        this.fileName = builder.fileName;
        this.publishDate = builder.publishDate;
        this.status = builder.status;
        this.reviews = builder.reviews;
    }
    
    private ArticleVersion() { }
    
    public String getArticleId() {
        return articleId;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public List<ArticleReview> getReviews() {
        return reviews;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public int getVersion() {
        return version;
    }

    /**
     * Creates builder to build {@link ArticleVersion}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link ArticleVersion}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String articleId;
        private int version;
        private String fileName;
        private Date publishDate;
        private ArticleStatus status;
        private List<ArticleReview> reviews = Collections.emptyList();

        private Builder() {
        }

        public Builder withArticleId(String articleId) {
            this.articleId = articleId;
            return this;
        }

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }

        public Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withPublishDate(Date publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public Builder withStatus(ArticleStatus status) {
            this.status = status;
            return this;
        }

        public Builder withReviews(List<ArticleReview> reviews) {
            this.reviews = reviews;
            return this;
        }

        public ArticleVersion build() {
            return new ArticleVersion(this);
        }
    }
    
}

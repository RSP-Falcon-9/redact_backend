package cz.falcon9.redact.backend.data.models.articles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table(name = "redact_arcticle_version")
@IdClass(ArticleVersionCompositeId.class)
public class ArticleVersion {
    
    /**
     * Builder to build {@link ArticleVersion}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String articleId;
        private int version;
        private String fileName;
        private Date publishDate;

        private Builder() {
        }

        public ArticleVersion build() {
            return new ArticleVersion(this);
        }

        public Builder withArticleId(String articleId) {
            this.articleId = articleId;
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

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }
    }

    /**
     * Creates builder to build {@link ArticleVersion}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

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
    
    @Generated("SparkTools")
    private ArticleVersion(Builder builder) {
        this.articleId = builder.articleId;
        this.version = builder.version;
        this.fileName = builder.fileName;
        this.publishDate = builder.publishDate;
    }
    
    public String getArticleId() {
        return articleId;
    }
    
    public String getFileName() {
        return fileName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public int getVersion() {
        return version;
    }
    
}

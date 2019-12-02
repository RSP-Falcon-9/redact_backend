package cz.falcon9.redact.backend.data.dtos.author;

import java.sql.Date;
import javax.annotation.Generated;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;

public class AuthorArticleVersion {

    /**
     * Builder to build {@link AuthorArticleVersion}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private int version;
        private String fileName;
        private Date publishDate;
        private ArticleStatus status;

        private Builder() {
        }

        public AuthorArticleVersion build() {
            return new AuthorArticleVersion(this);
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

        public Builder withVersion(int version) {
            this.version = version;
            return this;
        }
    }

    /**
     * Creates builder to build {@link AuthorArticleVersion}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
   
    private int version;
    
    private String fileName;

    private Date publishDate;

    private ArticleStatus status;

    @Generated("SparkTools")
    private AuthorArticleVersion(Builder builder) {
        this.version = builder.version;
        this.fileName = builder.fileName;
        this.publishDate = builder.publishDate;
        this.status = builder.status;
    }

    public String getFileName() {
        return fileName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public int getVersion() {
        return version;
    }
    
}

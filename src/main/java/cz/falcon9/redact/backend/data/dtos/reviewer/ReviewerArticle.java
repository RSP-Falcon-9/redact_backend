package cz.falcon9.redact.backend.data.dtos.reviewer;

import java.sql.Date;

import cz.falcon9.redact.backend.data.articles.ArticleStatus;
import javax.annotation.Generated;

public class ReviewerArticle {
    
    /**
     * Builder to build {@link ReviewerArticle}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private String name;
        private int version;
        private String fileName;
        private Date publishDate;
        private ArticleStatus status;

        private Builder() {
        }

        public ReviewerArticle build() {
            return new ReviewerArticle(this);
        }

        public Builder withFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
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
     * Creates builder to build {@link ReviewerArticle}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    private String id;
    
    private String name;

    private int version;

    private String fileName;

    private Date publishDate;

    private ArticleStatus status;

    @Generated("SparkTools")
    private ReviewerArticle(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.version = builder.version;
        this.fileName = builder.fileName;
        this.publishDate = builder.publishDate;
        this.status = builder.status;
    }

    public String getFileName() {
        return fileName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

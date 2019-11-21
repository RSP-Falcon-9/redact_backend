package cz.falcon9.redact.backend.data.dtos.author;

import java.sql.Date;
import javax.annotation.Generated;

public class AuthorArticleVersion {

    /**
     * Builder to build {@link AuthorArticleVersion}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private int version;
        private String fileName;
        private Date publishDate;

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

    @Generated("SparkTools")
    private AuthorArticleVersion(Builder builder) {
        this.version = builder.version;
        this.fileName = builder.fileName;
        this.publishDate = builder.publishDate;
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

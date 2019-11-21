package cz.falcon9.redact.backend.data.dtos.author;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class AuthorArticle {

    private String name;
    
    private List<AuthorArticleVersion> versions;

    @Generated("SparkTools")
    private AuthorArticle(Builder builder) {
        this.name = builder.name;
        this.versions = builder.versions;
    }

    public String getName() {
        return name;
    }

    public List<AuthorArticleVersion> getVersions() {
        return versions;
    }

    /**
     * Creates builder to build {@link AuthorArticle}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link AuthorArticle}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String name;
        private List<AuthorArticleVersion> versions = Collections.emptyList();

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withVersions(List<AuthorArticleVersion> versions) {
            this.versions = versions;
            return this;
        }

        public AuthorArticle build() {
            return new AuthorArticle(this);
        }
    }
    
}

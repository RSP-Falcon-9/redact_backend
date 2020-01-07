package cz.falcon9.redact.backend.data.dtos.author;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class AuthorArticle {

    /**
     * Builder to build {@link AuthorArticle}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private String name;
        private List<AuthorArticleVersion> versions = Collections.emptyList();
        private Integer edition;

        private Builder() {
        }

        public AuthorArticle build() {
            return new AuthorArticle(this);
        }

        public Builder withEdition(Integer edition) {
            this.edition = edition;
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

        public Builder withVersions(List<AuthorArticleVersion> versions) {
            this.versions = versions;
            return this;
        }
    }
    
    /**
     * Creates builder to build {@link AuthorArticle}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String id;
    
    private String name;

    private List<AuthorArticleVersion> versions;

    private Integer edition;

    @Generated("SparkTools")
    private AuthorArticle(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.versions = builder.versions;
        this.edition = builder.edition;
    }

    public Integer getEdition() {
        return edition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<AuthorArticleVersion> getVersions() {
        return versions;
    }
    
}

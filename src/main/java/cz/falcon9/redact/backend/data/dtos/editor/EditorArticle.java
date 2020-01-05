package cz.falcon9.redact.backend.data.dtos.editor;

import java.util.List;

import cz.falcon9.redact.backend.data.dtos.author.AuthorArticleVersion;
import javax.annotation.Generated;
import java.util.Collections;

public class EditorArticle {
    
    /**
     * Builder to build {@link EditorArticle}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private String name;
        private String authorId;
        private List<AuthorArticleVersion> versions = Collections.emptyList();
        private int edition;

        private Builder() {
        }

        public EditorArticle build() {
            return new EditorArticle(this);
        }

        public Builder withAuthorId(String authorId) {
            this.authorId = authorId;
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
        
        public Builder withEdition(int edition) {
            this.edition = edition;
            return this;
        }
    }

    /**
     * Creates builder to build {@link EditorArticle}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String id;

    private String name;

    private String authorId;
    
    private List<AuthorArticleVersion> versions;
    
    private int edition;
    
    @Generated("SparkTools")
    private EditorArticle(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.authorId = builder.authorId;
        this.versions = builder.versions;
        this.edition = builder.edition;
    }

    public String getAuthorId() {
        return authorId;
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
    
    public int getEdition() {
        return edition;
    }
    
}

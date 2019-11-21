package cz.falcon9.redact.backend.data.models.articles;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.falcon9.redact.backend.data.models.auth.User;
import javax.annotation.Generated;
import java.util.Collections;

@Entity
@Table(name = "redact_article")
public class Article {
    
    /**
     * Builder to build {@link Article}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String id;
        private String name;
        private User user;
        private List<ArticleVersion> versions = Collections.emptyList();

        private Builder() {
        }

        public Article build() {
            return new Article(this);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withVersions(List<ArticleVersion> versions) {
            this.versions = versions;
            return this;
        }
    }

    /**
     * Creates builder to build {@link Article}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="id")
    private List<ArticleVersion> versions;

    @Generated("SparkTools")
    private Article(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.user = builder.user;
        this.versions = builder.versions;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public List<ArticleVersion> getVersions() {
        return versions;
    }
    
}

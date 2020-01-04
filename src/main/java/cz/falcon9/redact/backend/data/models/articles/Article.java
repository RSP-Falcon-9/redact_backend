package cz.falcon9.redact.backend.data.models.articles;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.data.models.editions.EditionEntity;

import javax.annotation.Generated;
import java.util.Collections;
import java.util.Comparator;

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
        private EditionEntity edition;

        private Builder() {
        }

        public Article build() {
            return new Article(this);
        }

        public Builder withEdition(EditionEntity edition) {
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
    @JoinColumn(name="author_id", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "articleId")
    private List<ArticleVersion> versions;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "edition", referencedColumnName= "id", nullable = true)
    private EditionEntity edition;

    private Article() { }

    @Generated("SparkTools")
    private Article(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.user = builder.user;
        this.versions = builder.versions;
        this.edition = builder.edition;
    }
    
    public EditionEntity getEdition() {
        return edition;
    }
    
    public String getId() {
        return id;
    }

    public ArticleVersion getLatestVersion() {
        return versions.stream().max(Comparator.comparingInt(ArticleVersion::getVersion)).get();
    }

    public String getName() {
        return name;
    }

    public User getUser() {
        return user;
    }

    public ArticleVersion getVersion(Integer version) {
        Optional<ArticleVersion> optionalVersion = versions.stream().filter(ver -> ver.getVersion() == version).findFirst();
        
        if (!optionalVersion.isPresent()) {
            return null;
        }
        
        return optionalVersion.get();
    }

    public List<ArticleVersion> getVersions() {
        return versions;
    }
    
}

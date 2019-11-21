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

@Entity
@Table(name = "redact_article")
public class Article {
    
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="id")
    private List<ArticleVersion> versions;

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

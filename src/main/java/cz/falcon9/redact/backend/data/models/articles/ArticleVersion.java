package cz.falcon9.redact.backend.data.models.articles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "redact_arcticle_version")
@IdClass(ArticleVersionCompositeId.class)
public class ArticleVersion {
    
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

package cz.falcon9.redact.backend.data.models.articles;

import java.io.Serializable;

public class ArticleVersionCompositeId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String article_id;

    private Integer version;

    ArticleVersionCompositeId() {}

    public ArticleVersionCompositeId(String article_id, Integer version) {
        this.article_id = article_id;
        this.version = version;
    }

    public String getArticle_id() {
        return article_id;
    }
    
    public Integer getVersion() {
        return version;
    }
    
}

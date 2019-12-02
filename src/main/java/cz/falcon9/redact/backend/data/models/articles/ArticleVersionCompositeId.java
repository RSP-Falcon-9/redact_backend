package cz.falcon9.redact.backend.data.models.articles;

import java.io.Serializable;

public class ArticleVersionCompositeId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String articleId;

    private Integer version;

    ArticleVersionCompositeId() {}

    public ArticleVersionCompositeId(String article_id, Integer version) {
        this.articleId = article_id;
        this.version = version;
    }

    public String getArticle_id() {
        return articleId;
    }
    
    public Integer getVersion() {
        return version;
    }
    
}

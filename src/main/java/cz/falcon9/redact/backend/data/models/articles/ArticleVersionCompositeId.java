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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((articleId == null) ? 0 : articleId.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArticleVersionCompositeId other = (ArticleVersionCompositeId) obj;
        if (articleId == null) {
            if (other.articleId != null)
                return false;
        } else if (!articleId.equals(other.articleId))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }
    
}

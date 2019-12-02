package cz.falcon9.redact.backend.data.dtos.editor;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class GetEditorArticlesResponse {
    
    private List<EditorArticle> articles;

    @Generated("SparkTools")
    private GetEditorArticlesResponse(Builder builder) {
        this.articles = builder.articles;
    }

    public List<EditorArticle> getArticles() {
        return articles;
    }

    /**
     * Creates builder to build {@link GetEditorArticlesResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link GetEditorArticlesResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<EditorArticle> articles = Collections.emptyList();

        private Builder() {
        }

        public Builder withArticles(List<EditorArticle> articles) {
            this.articles = articles;
            return this;
        }

        public GetEditorArticlesResponse build() {
            return new GetEditorArticlesResponse(this);
        }
    }
    
}

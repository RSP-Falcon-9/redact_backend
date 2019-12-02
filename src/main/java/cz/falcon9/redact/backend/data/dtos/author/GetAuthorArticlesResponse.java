package cz.falcon9.redact.backend.data.dtos.author;

import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class GetAuthorArticlesResponse {

    /**
     * Builder to build {@link GetAuthorArticlesResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<AuthorArticle> articles = Collections.emptyList();

        private Builder() {
        }

        public GetAuthorArticlesResponse build() {
            return new GetAuthorArticlesResponse(this);
        }

        public Builder withArticles(List<AuthorArticle> articles) {
            this.articles = articles;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetAuthorArticlesResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private List<AuthorArticle> articles;

    @Generated("SparkTools")
    private GetAuthorArticlesResponse(Builder builder) {
        this.articles = builder.articles;
    }

    public List<AuthorArticle> getArticles() {
        return articles;
    }
    
}

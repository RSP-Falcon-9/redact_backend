package cz.falcon9.redact.backend.data.dtos.reviewer;

import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class GetReviewerArticlesResponse {

    /**
     * Builder to build {@link GetReviewerArticlesResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<ReviewerArticle> articles = Collections.emptyList();

        private Builder() {
        }

        public GetReviewerArticlesResponse build() {
            return new GetReviewerArticlesResponse(this);
        }

        public Builder withArticles(List<ReviewerArticle> articles) {
            this.articles = articles;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetReviewerArticlesResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private List<ReviewerArticle> articles;

    @Generated("SparkTools")
    private GetReviewerArticlesResponse(Builder builder) {
        this.articles = builder.articles;
    }

    public List<ReviewerArticle> getArticles() {
        return articles;
    }
    
}

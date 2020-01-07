package cz.falcon9.redact.backend.data.dtos.author;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class GetAuthorArticleDetail {

    /**
     * Builder to build {@link GetAuthorArticleDetail}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String name;
        private Integer edition;
        private List<AuthorArticleReview> reviews = Collections.emptyList();

        private Builder() {
        }

        public GetAuthorArticleDetail build() {
            return new GetAuthorArticleDetail(this);
        }

        public Builder withEdition(Integer edition) {
            this.edition = edition;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withReviews(List<AuthorArticleReview> reviews) {
            this.reviews = reviews;
            return this;
        }
    }
    
    /**
     * Creates builder to build {@link GetAuthorArticleDetail}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String name;

    private Integer edition;

    private List<AuthorArticleReview> reviews;

    @Generated("SparkTools")
    private GetAuthorArticleDetail(Builder builder) {
        this.name = builder.name;
        this.edition = builder.edition;
        this.reviews = builder.reviews;
        this.edition = builder.edition;
    }

    public Integer getEdition() {
        return edition;
    }

    public String getName() {
        return name;
    }

    public List<AuthorArticleReview> getReviews() {
        return reviews;
    }
    
}

package cz.falcon9.redact.backend.data.dtos.author;

import javax.annotation.Generated;

public class GetArticleDetail {

    private String name;

    @Generated("SparkTools")
    private GetArticleDetail(Builder builder) {
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    /**
     * Creates builder to build {@link GetArticleDetail}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link GetArticleDetail}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String name;

        private Builder() {
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public GetArticleDetail build() {
            return new GetArticleDetail(this);
        }
    }
    
}

package cz.falcon9.redact.backend.data.dtos;

import java.util.List;

import javax.annotation.Generated;

public class GetEditionsResponse {

    /**
     * Builder to build {@link GetEditionsResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<Edition> editions;

        private Builder() {
        }

        public GetEditionsResponse build() {
            return new GetEditionsResponse(this);
        }

        public Builder withEditions(List<Edition> editions) {
            this.editions = editions;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetEditionsResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private List<Edition> editions;

    @Generated("SparkTools")
    private GetEditionsResponse(Builder builder) {
        this.editions = builder.editions;
    }

    public List<Edition> getEditions() {
        return editions;
    }
    
}

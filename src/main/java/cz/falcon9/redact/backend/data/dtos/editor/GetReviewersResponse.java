package cz.falcon9.redact.backend.data.dtos.editor;

import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class GetReviewersResponse {

    /**
     * Builder to build {@link GetReviewersResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<Reviewer> reviewers = Collections.emptyList();

        private Builder() {
        }

        public GetReviewersResponse build() {
            return new GetReviewersResponse(this);
        }

        public Builder withReviewers(List<Reviewer> reviewers) {
            this.reviewers = reviewers;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetReviewersResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private List<Reviewer> reviewers;

    @Generated("SparkTools")
    private GetReviewersResponse(Builder builder) {
        this.reviewers = builder.reviewers;
    }

    public List<Reviewer> getReviewers() {
        return reviewers;
    }
    
}

package cz.falcon9.redact.backend.data.dtos.unauthenticated;

import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class GetArchivesResponse {

    /**
     * Builder to build {@link GetArchivesResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<String> archives = Collections.emptyList();

        private Builder() {
        }

        public GetArchivesResponse build() {
            return new GetArchivesResponse(this);
        }

        public Builder withArchives(List<String> archives) {
            this.archives = archives;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetArchivesResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private List<String> archives;

    @Generated("SparkTools")
    private GetArchivesResponse(Builder builder) {
        this.archives = builder.archives;
    }

    public List<String> getArchives() {
        return archives;
    }
    
}

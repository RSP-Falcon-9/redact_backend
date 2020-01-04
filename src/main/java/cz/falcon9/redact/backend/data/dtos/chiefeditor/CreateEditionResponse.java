package cz.falcon9.redact.backend.data.dtos.chiefeditor;

import cz.falcon9.redact.backend.data.dtos.chiefeditor.structures.Edition;
import javax.annotation.Generated;

public class CreateEditionResponse {

    /**
     * Builder to build {@link CreateEditionResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private Edition edition;

        private Builder() {
        }

        public CreateEditionResponse build() {
            return new CreateEditionResponse(this);
        }

        public Builder withEdition(Edition edition) {
            this.edition = edition;
            return this;
        }
    }

    /**
     * Creates builder to build {@link CreateEditionResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private Edition edition;

    @Generated("SparkTools")
    private CreateEditionResponse(Builder builder) {
        this.edition = builder.edition;
    }

    public Edition getEdition() {
        return edition;
    }
    
}

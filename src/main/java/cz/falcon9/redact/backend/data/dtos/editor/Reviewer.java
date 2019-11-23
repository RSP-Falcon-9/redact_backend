package cz.falcon9.redact.backend.data.dtos.editor;

import javax.annotation.Generated;

public class Reviewer {

    /**
     * Builder to build {@link Reviewer}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String userName;

        private Builder() {
        }

        public Reviewer build() {
            return new Reviewer(this);
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }
    }

    /**
     * Creates builder to build {@link Reviewer}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    private String userName;

    @Generated("SparkTools")
    private Reviewer(Builder builder) {
        this.userName = builder.userName;
    }

    public String getUserName() {
        return userName;
    }
    
}

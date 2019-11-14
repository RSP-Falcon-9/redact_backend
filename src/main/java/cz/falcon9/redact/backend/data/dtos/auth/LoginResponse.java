package cz.falcon9.redact.backend.data.dtos.auth;

import javax.annotation.Generated;

public class LoginResponse {

    private String authorization;

    @Generated("SparkTools")
    private LoginResponse(Builder builder) {
        this.authorization = builder.authorization;
    }

    public String getAuthorization() {
        return authorization;
    }

    /**
     * Creates builder to build {@link LoginResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link LoginResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String authorization;

        private Builder() {
        }

        public Builder withAuthorization(String authorization) {
            this.authorization = authorization;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(this);
        }
    }

}

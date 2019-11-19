package cz.falcon9.redact.backend.data.dtos.admin;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class GetUsersResponse {

    private List<UserDetail> users;

    @Generated("SparkTools")
    private GetUsersResponse(Builder builder) {
        this.users = builder.users;
    }
    
    public List<UserDetail> getUsers() {
        return users;
    }

    /**
     * Creates builder to build {@link GetUsersResponse}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link GetUsersResponse}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private List<UserDetail> users = Collections.emptyList();

        private Builder() {
        }

        public Builder withUsers(List<UserDetail> users) {
            this.users = users;
            return this;
        }

        public GetUsersResponse build() {
            return new GetUsersResponse(this);
        }
    }
    
}

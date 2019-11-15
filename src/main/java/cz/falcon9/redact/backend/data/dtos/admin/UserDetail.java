package cz.falcon9.redact.backend.data.dtos.admin;

import java.util.List;

import javax.annotation.Generated;
import java.util.Collections;

public class UserDetail {
    
    private String userName;
    
    private List<String> roles;

    @Generated("SparkTools")
    private UserDetail(Builder builder) {
        this.userName = builder.userName;
        this.roles = builder.roles;
    }
    
    public String getUserName() {
        return userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    /**
     * Creates builder to build {@link UserDetail}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link UserDetail}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String userName;
        private List<String> roles = Collections.emptyList();

        private Builder() {
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserDetail build() {
            return new UserDetail(this);
        }
    }
    
}

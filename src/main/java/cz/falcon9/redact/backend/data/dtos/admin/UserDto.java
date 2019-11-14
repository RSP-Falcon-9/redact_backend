package cz.falcon9.redact.backend.data.dtos.admin;

import javax.annotation.Generated;

public class UserDto {
    
    private String userName;

    public String getUserName() {
        return userName;
    }
    
    @Generated("SparkTools")
    private UserDto(Builder builder) {
        this.userName = builder.userName;
    }

    /**
     * Creates builder to build {@link UserDto}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Builder to build {@link UserDto}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String userName;

        private Builder() {
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
    
}

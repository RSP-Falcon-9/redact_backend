package cz.falcon9.redact.backend.data.models.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table(name = "redact_user_roles")
@IdClass(UserRoleCompositeId.class)
public class UserRole {
    
    /**
     * Builder to build {@link UserRole}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String username;
        private String role;

        private Builder() {
        }

        public UserRole build() {
            return new UserRole(this);
        }

        public Builder withRole(String role) {
            this.role = role;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }
    }

    /**
     * Creates builder to build {@link UserRole}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    @Id 
	private String username;

    @Id
    private String role;

    @Generated("SparkTools")
    private UserRole(Builder builder) {
        this.username = builder.username;
        this.role = builder.role;
    }

    private UserRole() { }
    
    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

}

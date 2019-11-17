package cz.falcon9.redact.backend.data.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.annotation.Generated;
import java.util.Collections;

@Entity
@Table(name = "redact_users")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "username")
    private List<UserRole> roles;
    
    @Generated("SparkTools")
    private User(Builder builder) {
        this.userName = builder.userName;
        this.password = builder.password;
        this.roles = builder.roles;
    }
    
    private User() {}

    public String getPassword() {
        return password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Creates builder to build {@link User}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder to build {@link User}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String userName;
        private String password;
        private List<UserRole> roles = Collections.emptyList();

        private Builder() {
        }

        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withRoles(List<UserRole> roles) {
            this.roles = roles;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

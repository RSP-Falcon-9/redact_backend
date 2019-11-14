package cz.falcon9.redact.backend.data.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "redact_users")
public class User {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "role")
    private List<UserRole> roles;

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

}

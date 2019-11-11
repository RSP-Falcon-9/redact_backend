package cz.falcon9.redact.backend.data.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.immutables.value.Value.Immutable;

@Entity
@Table(name = "rsp_users")
public class User {

    @Id
    @Column(name="username")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name="password", nullable = false)
    private String password;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

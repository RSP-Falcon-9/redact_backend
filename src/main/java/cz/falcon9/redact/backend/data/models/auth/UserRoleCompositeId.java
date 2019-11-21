package cz.falcon9.redact.backend.data.models.auth;

import java.io.Serializable;

public class UserRoleCompositeId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String role;

    public UserRoleCompositeId(String username, String role) {
        this.username = username;
        this.role = role;
    }
    
    UserRoleCompositeId() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserRoleCompositeId other = (UserRoleCompositeId) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

}

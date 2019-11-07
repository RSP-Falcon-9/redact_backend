package cz.falcon9.redact.backend.data.auth;

import java.util.List;

import org.immutables.value.Value.Immutable;

@Immutable
public interface LoginResponse {

    String getAuthorization();
    List<String> getRoles();
    
}

package cz.falcon9.redact.backend.data.dtos.auth;

import org.immutables.value.Value.Immutable;

@Immutable
public interface LoginResponse {

    String getAuthorization();
    
}

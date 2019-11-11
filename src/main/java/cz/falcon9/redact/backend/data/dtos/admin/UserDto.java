package cz.falcon9.redact.backend.data.dtos.admin;

import org.immutables.value.Value.Immutable;

@Immutable
public interface UserDto {
    
    String getUserName();
    
}

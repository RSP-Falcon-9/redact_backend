package cz.falcon9.redact.backend.data.dtos.admin;

import java.util.List;

import org.immutables.value.Value.Immutable;

@Immutable
public interface GetUsersResponse {

    List<UserDto> getUsers();
    
}

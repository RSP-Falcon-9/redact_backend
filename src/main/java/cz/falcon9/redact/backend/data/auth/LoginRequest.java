package cz.falcon9.redact.backend.data.auth;

import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Immutable
@Style(builder = "new") // builder has to have constructor
@JsonDeserialize(builder = ImmutableLoginRequest.Builder.class)
public interface LoginRequest {
    
    String getUserName();
    String getPassword();
    
}

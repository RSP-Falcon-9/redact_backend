package cz.falcon9.redact.backend.utils;

import java.util.concurrent.TimeUnit;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/login";

    public static final String TOKEN_HEADER = "authorization";
    public static final String TOKEN_PREFIX = "bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "redact-project";
    public static final String TOKEN_AUDIENCE = "redact-users";
    public static final Long TOKEN_EXPIRATION = TimeUnit.DAYS.toMillis(7);

}

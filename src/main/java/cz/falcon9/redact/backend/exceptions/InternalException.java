package cz.falcon9.redact.backend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InternalException(String message) {
        super(message);
    }
    
}

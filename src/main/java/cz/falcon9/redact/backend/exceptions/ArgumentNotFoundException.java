package cz.falcon9.redact.backend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArgumentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ArgumentNotFoundException(String message) {
        super(message);
    }
    
}

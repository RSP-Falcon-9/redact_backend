package cz.falcon9.redact.backend.data.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BaseDto<T> {

    @JsonUnwrapped
    @JsonInclude(Include.NON_NULL)
    private T otherData;
    private String message;

    public BaseDto(String message) {
        this.otherData = null;
        this.message = message;
    }
    
    public BaseDto(T otherData, String message) {
        this.otherData = otherData;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public T getOtherData() {
        return otherData;
    }

}

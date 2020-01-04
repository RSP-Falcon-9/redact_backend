package cz.falcon9.redact.backend.data.dtos.chiefeditor;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateEditionRequest {

    @NotEmpty
    private String description;
    
    @NotNull
    private ZonedDateTime deadline;
    
    public String getDescription() {
        return description;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }
    
}

package cz.falcon9.redact.backend.data.dtos.chiefeditor.structures;

import java.time.ZonedDateTime;
import javax.annotation.Generated;

public class ChiefEditorEdition {
    
    /**
     * Builder to build {@link ChiefEditorEdition}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private Integer number;
        private String description;
        private ZonedDateTime deadline;
        private boolean archived;

        private Builder() {
        }

        public ChiefEditorEdition build() {
            return new ChiefEditorEdition(this);
        }

        public Builder withArchived(boolean archived) {
            this.archived = archived;
            return this;
        }

        public Builder withDeadline(ZonedDateTime deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }
    }
    
    /**
     * Creates builder to build {@link ChiefEditorEdition}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    private Integer number;
    private String description;
    private ZonedDateTime deadline;
    private boolean archived;
    
    @Generated("SparkTools")
    private ChiefEditorEdition(Builder builder) {
        this.number = builder.number;
        this.description = builder.description;
        this.deadline = builder.deadline;
        this.archived = builder.archived;
    }
    
    public ZonedDateTime getDeadline() {
        return deadline;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Integer getNumber() {
        return number;
    }
    
    public boolean isArchived() {
        return archived;
    }
    
}

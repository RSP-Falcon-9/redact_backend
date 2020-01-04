package cz.falcon9.redact.backend.data.dtos.chiefeditor.structures;

import java.time.ZonedDateTime;
import javax.annotation.Generated;

public class Edition {
    
    /**
     * Builder to build {@link Edition}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private Integer id;
        private String description;
        private ZonedDateTime deadline;
        private boolean archived;

        private Builder() {
        }

        public Edition build() {
            return new Edition(this);
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

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }
    }
    
    /**
     * Creates builder to build {@link Edition}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    private Integer id;
    private String description;
    private ZonedDateTime deadline;
    private boolean archived;
    
    @Generated("SparkTools")
    private Edition(Builder builder) {
        this.id = builder.id;
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
    
    public Integer getId() {
        return id;
    }
    
    public boolean isArchived() {
        return archived;
    }
    
}

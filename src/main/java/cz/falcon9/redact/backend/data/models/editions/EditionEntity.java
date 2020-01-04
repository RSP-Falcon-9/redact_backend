package cz.falcon9.redact.backend.data.models.editions;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.annotation.Generated;

@Entity
@Table(name = "redact_edition")
public class EditionEntity {

    /**
     * Builder to build {@link EditionEntity}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private Integer id;
        private String description;
        private ZonedDateTime deadline;
        private boolean archived;

        private Builder() {
        }

        public EditionEntity build() {
            return new EditionEntity(this);
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
     * Creates builder to build {@link EditionEntity}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column
    private ZonedDateTime deadline;

    @Column
    private boolean archived;

    @Generated("SparkTools")
    private EditionEntity(Builder builder) {
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

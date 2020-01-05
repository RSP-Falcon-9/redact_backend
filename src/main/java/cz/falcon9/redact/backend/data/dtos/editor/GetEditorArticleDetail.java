package cz.falcon9.redact.backend.data.dtos.editor;

import java.util.List;
import javax.annotation.Generated;
import java.util.Collections;

public class GetEditorArticleDetail {
    
    /**
     * Builder to build {@link GetEditorArticleDetail}.
     */
    @Generated("SparkTools")
    public static final class Builder {
        private String name;
        private Integer edition;
        private List<EditorArticleReview> reviews = Collections.emptyList();

        private Builder() {
        }

        public GetEditorArticleDetail build() {
            return new GetEditorArticleDetail(this);
        }

        public Builder withEdition(Integer edition) {
            this.edition = edition;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withReviews(List<EditorArticleReview> reviews) {
            this.reviews = reviews;
            return this;
        }
    }

    /**
     * Creates builder to build {@link GetEditorArticleDetail}.
     * @return created builder
     */
    @Generated("SparkTools")
    public static Builder builder() {
        return new Builder();
    }
    
    private String name;

    private Integer edition;

    private List<EditorArticleReview> reviews;

    @Generated("SparkTools")
    private GetEditorArticleDetail(Builder builder) {
        this.name = builder.name;
        this.edition = builder.edition;
        this.reviews = builder.reviews;
        this.edition = builder.edition;
    }

    public Integer getEdition() {
        return edition;
    }

    public String getName() {
        return name;
    }

    public List<EditorArticleReview> getReviews() {
        return reviews;
    }
    
}

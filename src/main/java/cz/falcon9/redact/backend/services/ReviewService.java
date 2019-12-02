package cz.falcon9.redact.backend.services;

public interface ReviewService {

    void switchAuthorReviewVisibility(String reviewId, boolean visibility);
    void appealReview(String reviewId, String appeal);
    
}

package cz.falcon9.redact.backend.services.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.repositories.ArticleReviewRepository;
import cz.falcon9.redact.backend.services.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ArticleReviewRepository reviewRepo;
    
    @Override
    public void switchAuthorReviewVisibility(String reviewId, boolean visibility) {
        Optional<ArticleReview> optionalReview = reviewRepo.findById(reviewId);
        
        if (!optionalReview.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find review with id %s.", reviewId));
        }
        
        ArticleReview review = optionalReview.get();
        review.setVisibleToAuthor(visibility);
        
        reviewRepo.save(review);
    }
    
    @Override
    public void appealReview(String reviewId, String appeal) {
        Optional<ArticleReview> optionalReview = reviewRepo.findById(reviewId);
        
        if (!optionalReview.isPresent()) {
            throw new ArgumentNotFoundException(String.format("Cannot find review with id %s.", reviewId));
        }
        
        ArticleReview review = optionalReview.get();
        if (review.getReviewStatus() != ArticleReviewStatus.REVIEWED) {
            throw new InvalidArgumentException("Cannot appeal new or already appealed review.");
        }
        
        review.setAppeal(appeal);
        review.setAppealDate(new Date(Calendar.getInstance().getTime().getTime()));
        review.setReviewStatus(ArticleReviewStatus.APPEAL);
        
        reviewRepo.save(review);
    }

}

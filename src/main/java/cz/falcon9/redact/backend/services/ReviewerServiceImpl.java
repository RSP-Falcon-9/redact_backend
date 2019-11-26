package cz.falcon9.redact.backend.services;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cz.falcon9.redact.backend.data.articles.ArticleReviewForm;
import cz.falcon9.redact.backend.data.articles.ArticleReviewStatus;
import cz.falcon9.redact.backend.data.models.articles.ArticleReview;
import cz.falcon9.redact.backend.data.models.auth.User;
import cz.falcon9.redact.backend.exceptions.ArgumentNotFoundException;
import cz.falcon9.redact.backend.exceptions.InvalidArgumentException;
import cz.falcon9.redact.backend.repositories.ArticleReviewRepository;
import cz.falcon9.redact.backend.repositories.UserRepository;

@Service
public class ReviewerServiceImpl implements ReviewerService {

    private final Logger log = LoggerFactory.getLogger(ReviewerServiceImpl.class);
    
    @Autowired
    ArticleReviewRepository articleReviewRepo;
    
    @Autowired
    UserRepository userRepo;
    
    @Override
    public void reviewArticle(String reviewId, ArticleReviewForm form) {      
        Optional<ArticleReview> optionalArticleReview = articleReviewRepo.findById(reviewId);
        if (!optionalArticleReview.isPresent()) {
            throw new ArgumentNotFoundException("Review doesn't exist.");
        }
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> optionalUser = userRepo.findById(currentUserName);
        if (!optionalUser.isPresent()) {
            log.debug("User is not present in repo, but in auth is: {}", authentication.getName());
            
            throw new InvalidArgumentException(String.format("User %s is not valid!", authentication.getName()));
        }
        
        ArticleReview articleReview = optionalArticleReview.get();
        if (!articleReview.getReviewer().getUserName().equals(currentUserName)) {
            throw new InvalidArgumentException("Review doesn't belong to the user!");
        }
        
        if (articleReview.getReviewStatus() == ArticleReviewStatus.REVIEWED) {
            throw new InvalidArgumentException("Review was already reviewed!");
        }
        
        articleReviewRepo.save(ArticleReview.builder()
                .withId(articleReview.getId())
                .withArticleId(articleReview.getArticleId())
                .withVersion(articleReview.getVersion())
                .withInterest(form.getInterest())
                .withOriginality(form.getOriginality())
                .withSpecializationLevel(form.getSpecializationLevel())
                .withLanguageLevel(form.getLanguageLevel())
                .withComment(form.getComment())
                .withReviewer(articleReview.getReviewer())
                .withReviewStatus(ArticleReviewStatus.REVIEWED)
                .withReviewDate(new Date(Calendar.getInstance().getTime().getTime()))
                .build());
    }

}

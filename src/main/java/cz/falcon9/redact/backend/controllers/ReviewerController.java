package cz.falcon9.redact.backend.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.falcon9.redact.backend.data.dtos.BaseDto;

@RestController
@RequestMapping("/reviewer")
@Secured("ROLE_REVIEWER")
public class ReviewerController {

    @GetMapping("/reviews")
    public BaseDto<Void> handleGetPendingReviews() {
        return new BaseDto<Void>(String.format("Successfully got article with id %s and version %s."));
    }
    
}

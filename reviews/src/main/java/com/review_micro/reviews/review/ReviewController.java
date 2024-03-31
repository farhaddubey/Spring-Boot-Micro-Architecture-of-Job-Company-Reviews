package com.review_micro.reviews.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;

    ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean isReviewSaved = reviewService.addReview(companyId, review);
        if(isReviewSaved)
            return new ResponseEntity<>("Review added Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review Not saved", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview( @PathVariable Long reviewId){
        return new ResponseEntity<>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    // @PutMapping("/reviews/{reviewId}")
    // public ResponseEntity<String> updateReview(@PathVariable Long companyId,
    //         @PathVariable Long reviewId, @RequestBody Review review){
    //             boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
    //             if(isReviewUpdated){
    //                 return new ResponseEntity<>("Review updated Successfully", HttpStatus.OK);
    //             }else{
    //                 return new ResponseEntity<>("Review Not updated !", HttpStatus.NOT_FOUND);

    //             }
    //         }

    // @DeleteMapping("/reviews/{reviewId}")
    // public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId){
    //     boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
    //     if (isReviewDeleted){
    //         return new ResponseEntity<>("Review deleted Successfully", HttpStatus.OK);

    //     }else{
    //         return new ResponseEntity<>("Not deleted", HttpStatus.NOT_FOUND);
    //     }
    // }
}

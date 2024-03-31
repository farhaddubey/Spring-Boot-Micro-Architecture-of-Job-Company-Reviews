package com.review_micro.reviews.review;
import java.util.List;
// method declaration is done in the review service interface class 

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    // boolean updateReview(Long companyId, Long reviewId, Review review);
    // boolean deleteReview(Long companyId, Long reviewId);


    
}
package com.flutterjunction.firstjobapp.review;

import com.flutterjunction.firstjobapp.job.Job;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean createReview(Long companyId,Review review);
    Review getReviewById(Long companyId, Long reviewId);


    boolean deleteReview(Long companyId, Long reviewId);
    boolean updateReview(Long companyId, Long reviewId,Review review);

}

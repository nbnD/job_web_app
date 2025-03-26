package com.flutterjunction.firstjobapp.review.impl;

import com.flutterjunction.firstjobapp.company.Company;
import com.flutterjunction.firstjobapp.company.CompanyService;
import com.flutterjunction.firstjobapp.job.Job;
import com.flutterjunction.firstjobapp.review.Review;
import com.flutterjunction.firstjobapp.review.ReviewRepository;
import com.flutterjunction.firstjobapp.review.ReviewService;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }


    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream().filter(review ->
                        review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);

    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
//        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
//        return reviews.removeIf(review -> review.getId().equals(reviewId));

        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if (review != null) {
                Company company = review.getCompany();
                company.getReview().remove(review);
                review.setCompany(null);
                companyService.updateCompany(companyId, company);
                reviewRepository.deleteById(reviewId);
                return true;
            } else {

                return false;
            }

//         Review review=getReviewById(companyId,reviewId);

        } else {
            return false;
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
//        System.out.println(companyService.getCompanyById(companyId) != null);
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }

}




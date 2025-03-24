package com.flutterjunction.firstjobapp.company;

import com.flutterjunction.firstjobapp.job.Job;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    void createCompany(Company company);
    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);
    boolean updateCompany(Long id,Company company);
//boolean getReviewById(Long id,Long reviewId);
//    List<Review> findAllReview(Long companyId);
//    void createReview(Review review);
//    Review getReviewById(Long reviewId);
//    boolean deleteReviewById(Long reviewId);
//    boolean updateReview(Long companyId,Long reviewId,Review review);
}

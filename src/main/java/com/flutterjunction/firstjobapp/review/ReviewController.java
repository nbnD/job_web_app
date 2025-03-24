package com.flutterjunction.firstjobapp.review;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.flutterjunction.firstjobapp.company.CompanyRepository;
import com.flutterjunction.firstjobapp.company.CompanyService;
import com.flutterjunction.firstjobapp.job.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isSuccess = reviewService.createReview(companyId, review);
        if (isSuccess) {
            return new ResponseEntity<>("Review added Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not Saved", HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId,@PathVariable Long id) {
        return new ResponseEntity<>(reviewService.getReviewById(companyId,id), HttpStatus.OK);

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
      boolean isDeleted=  reviewService.deleteReview(companyId,reviewId);
      if(isDeleted){
          return new ResponseEntity<>("Deleted Review Successfully", HttpStatus.OK);
      }else{
          return new ResponseEntity<>("Review delete failed", HttpStatus.BAD_REQUEST);
      }

    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updatedReview) {

//        reviewService.getReviewById(companyId,reviewId);

        boolean isUpdated = reviewService.updateReview(companyId,reviewId, updatedReview);
        if (isUpdated)
            return new ResponseEntity<>("Review updated  SuccessFully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Review update Fail",HttpStatus.NOT_FOUND);
    }

    public boolean updateJob(Long id, Job job,Review review) {
        return false;
    }
}

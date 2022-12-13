package ecommerceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.Review;

import ecommerceBackend.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Aggregate root

    @GetMapping("/reviews")
    public CollectionModel<EntityModel<Review>> all() {
    	return reviewService.getAllReviews();
    }

    // Single review
    @GetMapping("/reviews/{id}")
    public EntityModel<Review> one(@PathVariable Long id) {
    	return reviewService.getReview(id);
    }
    
    @PostMapping("/reviews/{item_id}")
    public ResponseEntity<?> newReview(@RequestBody Review newReview, @PathVariable Long item_id) {
    	return reviewService.addNewReview(newReview, item_id);
    }
    
    @PutMapping("/reviews/{id}")
    public ResponseEntity<?> replaceReview(@RequestBody Review newReview, @PathVariable Long id) {
    	return reviewService.replaceReview(newReview, id);
    }
    
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
    	return reviewService.deleteReview(id);
    }
    
//
}

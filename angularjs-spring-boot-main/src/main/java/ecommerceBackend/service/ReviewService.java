package ecommerceBackend.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ecommerceBackend.assembler.ReviewModelAssembler;
import ecommerceBackend.controller.ReviewController;
import ecommerceBackend.entity.Item;
import ecommerceBackend.entity.Review;
import ecommerceBackend.exception.ReviewNotFoundException;
import ecommerceBackend.repository.ItemRepository;
import ecommerceBackend.repository.ReviewRepository;

@Service
public class ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final ItemRepository itemRepository;
	private ReviewModelAssembler assembler;
	
	public ReviewService(ReviewRepository reviewRepository, ReviewModelAssembler assembler,
						 ItemRepository itemRepository) {
		this.reviewRepository = reviewRepository;
		this.assembler = assembler;
		this.itemRepository = itemRepository;
	}
	
//	@GetMapping("/reviews")
	public CollectionModel<EntityModel<Review>> getAllReviews() {
		List<EntityModel<Review>> reviews = reviewRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reviews, linkTo(methodOn(ReviewController.class).all()).withSelfRel());
	}
	
//	@GetMapping("/reviews/{id}")
	public EntityModel<Review> getReview(@PathVariable Long id) {

        Review review = reviewRepository.findById(id) //
                .orElseThrow(() -> new ReviewNotFoundException(id));

        return assembler.toModel(review);
    }
	
//	@PostMapping("/reviews/{item_id}")
	public ResponseEntity<?> addNewReview(@RequestBody Review newReview, @PathVariable Long item_id){
		
		Item item = itemRepository.findById(item_id).orElseThrow(() -> new ReviewNotFoundException(item_id));
		item.getReviews().add(newReview);
		EntityModel<Review> entityModel = assembler.toModel(reviewRepository.save(newReview));
        
		return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
	}
	
//	@PutMapping("/reviews/{id}")
	public ResponseEntity<?> replaceReview(@RequestBody Review newReview, @PathVariable Long id){
		Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
//		
		review.setRating(newReview.getRating());
		review.setTitle(newReview.getTitle());
		review.setComment(newReview.getComment());
		
		EntityModel<Review> entityModel = assembler.toModel(reviewRepository.save(review));
		
		return ResponseEntity 
	              .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	              .body(entityModel);
	}
	
//	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<?> deleteReview(@PathVariable Long id) {
    	reviewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

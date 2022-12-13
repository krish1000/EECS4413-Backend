package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.ReviewController;
import ecommerceBackend.entity.Review;
import ecommerceBackend.service.ReviewService;


//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class ReviewModelAssembler implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public EntityModel<Review> toModel(Review review) {

        return EntityModel.of(review, //
                linkTo(methodOn(ReviewService.class).getReview(review.getId())).withSelfRel(), linkTo(methodOn(ReviewService.class).getAllReviews()).withRel("reviews"));
    }
}

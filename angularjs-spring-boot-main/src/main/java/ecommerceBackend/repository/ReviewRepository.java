package ecommerceBackend.repository;

//import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.hateoas.EntityModel; //ignore 
import org.springframework.stereotype.Repository;

import ecommerceBackend.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

//	public ItemRepository() {
//		
//	}

	//ignore this for now -krish
//	public Object save(Item newItem) {
//		
//		return null;
//	}
//
//	public Collection<EntityModel<Item>> findAll() {
//	
//		return null;
//	}
//
//	public Object findById(Long id) {
//		
//		return null;
//	}

}
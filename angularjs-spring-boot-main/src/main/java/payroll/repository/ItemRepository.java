package payroll.repository;

//import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.hateoas.EntityModel; //ignore 
import org.springframework.stereotype.Repository;


import payroll.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

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

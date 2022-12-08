package ecommerceBackend.repository;

//import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.hateoas.EntityModel; //ignore 
import org.springframework.stereotype.Repository;

import ecommerceBackend.entity.Address;


//import ecommerceBackend.entity.Item;

// Local URL: http://localhost:8080/demo/items for get

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

//	

}

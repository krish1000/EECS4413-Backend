package ecommerceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBackend.entity.Address;
import ecommerceBackend.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private final AddressService addressService;
    
    public AddressController(AddressService addressService) {
    	this.addressService = addressService;
    }

    // Aggregate root

    @GetMapping("/addresses")
    public CollectionModel<EntityModel<Address>> all() {
    	return addressService.all();
    }

    // Single item
    @GetMapping("/addresses/{id}")
    public EntityModel<Address> one(@PathVariable Long id) {
    	return addressService.one(id);
    }
    
    @PostMapping("/addresses/{user_id}")
    public ResponseEntity<?> newItem(@RequestBody Address newEntity, @PathVariable Long user_id) {
    	return addressService.newItem(newEntity, user_id);
    }
    
    @PutMapping("/addresses/{address_id}")
    public ResponseEntity<?> updateItem(@RequestBody Address newEntity, @PathVariable Long address_id) {
    	return addressService.replaceItem(newEntity, address_id);
    }
    
    
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
      return addressService.deleteAddress(id);
    }

}
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

import ecommerceBackend.assembler.AddressModelAssembler;
import ecommerceBackend.controller.AddressController;
import ecommerceBackend.entity.Address;
import ecommerceBackend.entity.User;
import ecommerceBackend.exception.AddressNotFoundException;
import ecommerceBackend.exception.UserNotFoundException;
import ecommerceBackend.repository.AddressRepository;
import ecommerceBackend.repository.UserRepository;

@Service
public class AddressService {
	
	private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private AddressModelAssembler assembler;
    
    public AddressService(AddressRepository repository, AddressModelAssembler assembler,
    					  UserRepository userRepository) {
        this.addressRepository = repository;
        this.assembler = assembler;
        this.userRepository = userRepository;
    }

    // Aggregate root
//    @GetMapping("/addresses")
    public CollectionModel<EntityModel<Address>> all() {

        List<EntityModel<Address>> items = addressRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(AddressController.class).all()).withSelfRel());
    }
    
    // Single item
//    @GetMapping("/addresses/{id}")
    public EntityModel<Address> one(@PathVariable Long id) {

        Address entity = addressRepository.findById(id) //
                .orElseThrow(() -> new AddressNotFoundException(id));

        return assembler.toModel(entity);
    }
    
    
//    @PostMapping("/addresses/{user_id}")
    public ResponseEntity<?> newItem(@RequestBody Address newAddress, @PathVariable Long user_id) {
    	
    	User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException(user_id));  
    	user.getAddresses().add(newAddress);
    	
    	EntityModel<Address> entityModel = assembler.toModel(addressRepository.save(newAddress));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }
    
//    @PutMapping("/addresses/{address_id}")
    public ResponseEntity<?> replaceItem(@RequestBody Address newEntity, @PathVariable Long address_id) {
    	
    	Address existingAddress = addressRepository.findById(address_id).orElseThrow(() -> new AddressNotFoundException(address_id));
	  	
    	existingAddress.setProvince(newEntity.getProvince());
    	existingAddress.setCountry(newEntity.getCountry());
    	existingAddress.setStreet(newEntity.getStreet());
    	existingAddress.setZip(newEntity.getZip());
    	existingAddress.setPhone(newEntity.getPhone());
    	
	  	EntityModel<Address> entityModel = assembler.toModel(addressRepository.save(newEntity));
	
	      return ResponseEntity 
	              .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	              .body(entityModel);
    }
  

//    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
      addressRepository.deleteById(id);
      return ResponseEntity.ok(null);
    }


}

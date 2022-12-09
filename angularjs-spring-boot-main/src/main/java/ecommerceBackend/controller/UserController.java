package ecommerceBackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBackend.assembler.UserModelAssembler;
import ecommerceBackend.entity.Address;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.User;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.exception.UserNotFoundException;
import ecommerceBackend.repository.AddressRepository;
import ecommerceBackend.repository.ShoppingCartRepository;
import ecommerceBackend.repository.UserRepository;

@RestController
public class UserController {
	private final UserRepository repository;
	private ShoppingCartRepository scRepository;
	private AddressRepository addressRepository;
    private UserModelAssembler assembler;
    
    public UserController(UserRepository repository, UserModelAssembler assembler, 
    					ShoppingCartRepository sc, AddressRepository addressRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.scRepository = sc;
        this.addressRepository = addressRepository;
    }

    // Aggregate root

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {

        List<EntityModel<User>> users = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {

    	EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
    	User user = repository.findById(newUser.getId()).orElseThrow(() -> new ShoppingCartNotFoundException(newUser.getId()));
//        System.out.println(user.getShoppingCartId());
//        System.out.println(user.getAddressId());
    	ShoppingCart sc = new ShoppingCart(newUser.getId());
        Address address = new Address();
        scRepository.save(sc);
        addressRepository.save(address);
//        System.out.println(newUser);
//        System.out.println(address.getId());
//        System.out.println(sc.getId());
//        newUser.setShoppingCart(sc);
        user.setShoppingCartId(sc.getId());
        user.setAddressId(address.getId());
//        System.out.println(user.getShoppingCartId());
//        System.out.println(user.getAddressId());
        repository.save(user);
        sc.setUserId(newUser.getId());
//        System.out.println(address.getId());
//        System.out.println(sc.getId());
//        System.out.println(newUser);
        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }


    // Single user
    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {

        User user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//    	System.out.println(id);
    	User user = repository.findById(id).orElseThrow(() -> new ShoppingCartNotFoundException(id));
//    	System.out.println("user id: " + user.getId());
    	scRepository.findById(user.getShoppingCartId()).orElseThrow(() -> new ShoppingCartNotFoundException(user.getShoppingCartId()));
//        System.out.println("hello");
    	repository.deleteById(id);
//        System.out.println("hello");
        return ResponseEntity.noContent().build();
    }
    
//
//    @PutMapping("/employees/{id}")
//    public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//        Employee updatedEmployee = repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
//                });
//        
//        EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
//        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
//        
//    }
//
}

package ecommerceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.User;

import ecommerceBackend.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Aggregate root

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
    	return userService.getAllUsers();
    }

    // Single user
    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
    	return userService.getUser(id);
    }
    
    @PostMapping("/users")
    public ResponseEntity<?> newUser(@RequestBody User newUser) {
    	return userService.addNewUser(newUser);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<?> replaceEmployee(@RequestBody User newUser, @PathVariable Long id) {
    	return userService.replaceUser(newUser, id);
    }
    
    // might not need this cause project doesnt say we need it
    // but it can be used to test stuff
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    	return userService.deleteUser(id);
    }
    
//
}

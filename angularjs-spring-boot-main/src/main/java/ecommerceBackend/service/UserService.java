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

import ecommerceBackend.assembler.UserModelAssembler;
import ecommerceBackend.controller.UserController;
import ecommerceBackend.entity.Event;
import ecommerceBackend.entity.User;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.exception.UserNotFoundException;
import ecommerceBackend.repository.EventRepository;
import ecommerceBackend.repository.UserRepository;
import java.util.Random;
@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final EventRepository eventRepository;
	private UserModelAssembler assembler;
	
	public UserService(UserRepository userRepository, UserModelAssembler assembler, EventRepository eventRepository) {
		this.userRepository = userRepository;
		this.assembler = assembler;
		this.eventRepository = eventRepository;
	}
	
//	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getAllUsers() {
		List<EntityModel<User>> users = userRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
//	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable Long id) {

        User user = userRepository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }
	
//	@PostMapping("/users")
	public ResponseEntity<?> addNewUser(@RequestBody User newUser){
		EntityModel<User> entityModel = assembler.toModel(userRepository.save(newUser));
//        String ipAddress = createRandomIpAddress();
        String description = "New User Created. Username: " + newUser.getUsername() + ".";
        Event event = new Event();
        event.setDescription(description);
//        event.setIpAddress(ipAddress);
		eventRepository.save(event);
		return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
	}
	
//	@PutMapping("/users/{id}")
	public ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id){
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		user.setEmail(newUser.getEmail());
		user.setFirstName(newUser.getFirstName());
		user.setLastName(newUser.getLastName());
		user.setPassword(newUser.getPassword());
		
		EntityModel<User> entityModel = assembler.toModel(userRepository.save(user));
		
		return ResponseEntity 
	              .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
	              .body(entityModel);
	}
	
//	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    	userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
	
//	public static String createRandomIpAddress() {
//        return randomNumber() + "." + randomNumber() + "." + randomNumber() + "." + randomNumber();
//    }
//
//    public static int randomNumber() {
//        return new Random().nextInt((255 - 1) + 1) + 1;
//    }
}

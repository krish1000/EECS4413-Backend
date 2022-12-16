package ecommerceBackend.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ecommerceBackend.assembler.ShoppingCartModelAssembler;
import ecommerceBackend.controller.ShoppingCartController;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.ShoppingCartItem;
import ecommerceBackend.entity.User;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.exception.UserNotFoundException;
import ecommerceBackend.repository.ShoppingCartRepository;
import ecommerceBackend.repository.UserRepository;

@Service
public class ShoppingCartService {
	
	private final ShoppingCartRepository repository;
//	private final UserRepository userRepository;
	private final ShoppingCartModelAssembler assembler;
	
	public ShoppingCartService(ShoppingCartRepository repository,ShoppingCartModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
//		this.userRepository = userRepository;
	}
	
//    @GetMapping("/shopping-carts")
    public CollectionModel<EntityModel<ShoppingCart>> all() {

        List<EntityModel<ShoppingCart>> shoppingCarts = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(shoppingCarts, linkTo(methodOn(ShoppingCartController.class).all()).withSelfRel());
    }
    
    // Single shoppingCart
//    @GetMapping("/shopping-carts/{id}")
    public EntityModel<ShoppingCart> one(@PathVariable("id") Long id) {

        ShoppingCart shoppingCart = repository.findById(id) //
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));

        return assembler.toModel(shoppingCart);
    }
    
    // we don't need this since we users only have one shopping cart
//    @PostMapping("/shopping-carts")
    public ResponseEntity<?> newShoppingCart(@RequestBody ShoppingCart newShoppingCart) {

        EntityModel<ShoppingCart> entityModel = assembler.toModel(repository.save(newShoppingCart));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }

	public ResponseEntity<?> deleteShoppingCart(Long id) {
		// TODO Auto-generated method stub
        ShoppingCart shoppingCart = repository.findById(id) //
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));
        
        shoppingCart.getShoppingCartItems().clear();
        repository.save(shoppingCart);
        
        return ResponseEntity.ok(null);
	}

//	public ResponseEntity<?> updateShoppingCart(ShoppingCart newShoppingCart, Long userId) {
//		// TODO Auto-generated method stub
//		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//
//		return null;
//	}


}

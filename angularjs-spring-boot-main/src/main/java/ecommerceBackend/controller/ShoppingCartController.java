package ecommerceBackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

import ecommerceBackend.assembler.ShoppingCartModelAssembler;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.repository.ShoppingCartRepository;
import ecommerceBackend.service.ShoppingCartService;


@RestController
public class ShoppingCartController {
	
	@Autowired
	private final ShoppingCartService shoppingCartService;
    
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    // Aggregate root

    @GetMapping("/shopping-carts")
    public CollectionModel<EntityModel<ShoppingCart>> all() {
    	return shoppingCartService.all();
    }
    
    // we don't need this since we create a shopping cart when we create a user
    @PostMapping("/shopping-carts")
    public ResponseEntity<?> newShoppingCart(@RequestBody ShoppingCart newShoppingCart) {
    	return shoppingCartService.newShoppingCart(newShoppingCart);
    }


    // Single shoppingCart
    @GetMapping("/shopping-carts/{id}")
    public EntityModel<ShoppingCart> one(@PathVariable("id") Long id) {
    	return shoppingCartService.one(id);
    }
    
//    @PutMapping("/shopping-carts/{id}")
//    public ResponseEntity<?> updateShoppingCart(@RequestBody ShoppingCart newShoppingCart, @PathVariable Long id) {
//    	return shoppingCartService.updateShoppingCart(newShoppingCart, id);
//    }
    
    //DELETE
    @PostMapping("/shopping-carts/{id}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable Long id){
    	return shoppingCartService.deleteShoppingCart(id);
    }
}

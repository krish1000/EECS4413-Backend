package ecommerceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBackend.entity.ShoppingCartItem;
import ecommerceBackend.service.ShoppingCartItemService;


@RestController
public class ShoppingCartItemController {

	@Autowired
	private final ShoppingCartItemService shoppingCartItemService;
    
    public ShoppingCartItemController(ShoppingCartItemService shoppingCartItemService) {
        this.shoppingCartItemService = shoppingCartItemService;
    }

    // Aggregate root

    @GetMapping("/shopping-cart-items")
    public CollectionModel<EntityModel<ShoppingCartItem>> all() {
    	return shoppingCartItemService.all();
    }
    
    // Single shoppingCartItem
    @GetMapping("/shopping-cart-items/{id}")
    public EntityModel<ShoppingCartItem> one(@PathVariable("id") Long id) {
    	return shoppingCartItemService.one(id);
    }
    
    @PostMapping("/shopping-cart-items/{shopping_cart_id}/{item_id}")
    public ResponseEntity<?> newShoppingCartItem(@RequestBody ShoppingCartItem newShoppingCartItem,
									    		@PathVariable("shopping_cart_id") Long scId,
												@PathVariable("item_id") Long itemId) {
	    return shoppingCartItemService.newShoppingCartItem(newShoppingCartItem, scId, itemId);    
    }
    
    @PutMapping("/shopping-cart-items/{shopping_cart_item_id}")
    public ResponseEntity<?> newShoppingCartItem(@RequestBody ShoppingCartItem newShoppingCartItem,
									    		@PathVariable("shopping_cart_item_id") Long shoppingCartItemId) {
	    return shoppingCartItemService.updateShoppingCartItem(newShoppingCartItem, shoppingCartItemId);   
    }
  
    @DeleteMapping("/shopping-cart-items/{id}")
	public ResponseEntity<?> deleteShoppingCartItem(@PathVariable Long id) {
    	return shoppingCartItemService.deleteShoppingCartItem(id);
	}

}

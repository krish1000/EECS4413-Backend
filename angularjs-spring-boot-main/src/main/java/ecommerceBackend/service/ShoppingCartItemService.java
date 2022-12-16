package ecommerceBackend.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ecommerceBackend.assembler.ShoppingCartItemModelAssembler;
import ecommerceBackend.controller.ShoppingCartItemController;
import ecommerceBackend.entity.Item;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.ShoppingCartItem;
import ecommerceBackend.exception.ItemNotFoundException;
import ecommerceBackend.exception.ShoppingCartItemNotFoundException;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.repository.ItemRepository;
import ecommerceBackend.repository.ShoppingCartItemRepository;
import ecommerceBackend.repository.ShoppingCartRepository;
import ecommerceBackend.repository.UserRepository;

@Service
public class ShoppingCartItemService {

	private final ShoppingCartItemRepository shoppingCartItemRepository;
	private final UserRepository userRepository;
	private final ShoppingCartRepository shoppingCartRepository;
	private final ItemRepository itemRepository;
	private final ShoppingCartItemModelAssembler assembler;
	
	public ShoppingCartItemService(ShoppingCartItemRepository shoppingCartItemRepository, ShoppingCartItemModelAssembler assembler,
								   ShoppingCartRepository shoppingCartRepository, ItemRepository itemRepository,
								   UserRepository userRepository) {
		this.shoppingCartItemRepository = shoppingCartItemRepository;
		this.shoppingCartRepository = shoppingCartRepository;
		this.itemRepository = itemRepository;
		this.assembler = assembler;
		this.userRepository = userRepository;
	}
	
//	@GetMapping("/shopping-cart-items")
    public CollectionModel<EntityModel<ShoppingCartItem>> all() {

        List<EntityModel<ShoppingCartItem>> shoppingCartItems = shoppingCartItemRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(shoppingCartItems, linkTo(methodOn(ShoppingCartItemController.class).all()).withSelfRel());
    }
    
 // Single shoppingCartItem
//  @GetMapping("/shopping-cart-items/{id}")
    public EntityModel<ShoppingCartItem> one(@PathVariable("id") Long id) {

        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(id) //
                .orElseThrow(() -> new ShoppingCartItemNotFoundException(id));

        return assembler.toModel(shoppingCartItem);
    }
    
//    @PostMapping("/shopping-cart-items/{shopping_cart_id}/{item_id}")
    public ResponseEntity<?> newShoppingCartItem(@RequestBody ShoppingCartItem newShoppingCartItem, 
    											@PathVariable("shopping_cart_id") Long scId,
    											@PathVariable("item_id") Long itemId) {
    	Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    	ShoppingCart shoppingCart = shoppingCartRepository.findById(scId).orElseThrow(() -> new ShoppingCartNotFoundException(scId));
    	
    	// might not need this
    	if (itemInShoppingCart(item, shoppingCart)) {
    		return ResponseEntity.badRequest().build();
    	}
    	
		newShoppingCartItem.setPrice(item.getPrice()*newShoppingCartItem.getQuantity());
		newShoppingCartItem.setItem(item);
		newShoppingCartItem.setItemId(item.getId()); 
		shoppingCart.getShoppingCartItems().add(newShoppingCartItem); 
        
    	EntityModel<ShoppingCartItem> entityModel = assembler.toModel(shoppingCartItemRepository.save(newShoppingCartItem));
    	
        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }
    
//  @PutMapping("/shopping-cart-items/{shopping_cart_item_id}")
    public ResponseEntity<?> updateShoppingCartItem(@RequestBody ShoppingCartItem newShoppingCartItem, 
  													@PathVariable("shopping_cart_item_id") Long shoppingCartItemId) {
	  	
	  	ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findById(shoppingCartItemId).orElseThrow(() -> new ShoppingCartItemNotFoundException(shoppingCartItemId));
	  	shoppingCartItem.setPrice(shoppingCartItem.getItem().getPrice() *newShoppingCartItem.getQuantity());
  		shoppingCartItem.setQuantity(newShoppingCartItem.getQuantity());
  		
  		EntityModel<ShoppingCartItem> entityModel = assembler.toModel(shoppingCartItemRepository.save(shoppingCartItem));;
	  	
  		return ResponseEntity 
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
          .body(entityModel);
    }
    
//    @DeleteMapping("/shopping-cart-items/{id}")
	public ResponseEntity<?> deleteShoppingCartItem(@PathVariable Long id) {
    	shoppingCartItemRepository.deleteById(id);
    	return ResponseEntity.ok(null);
    }
	
	// might not need this
	public boolean itemInShoppingCart(Item item, ShoppingCart shoppingCart) {
		for (ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems()) {
			if (shoppingCartItem.getItemId() == item.getId()) {
				return true;
			}
		}
		return false;
	}
	
//	public static String createRandomIpAddress() {
//        return randomNumber() + "." + randomNumber() + "." + randomNumber() + "." + randomNumber();
//    }
//
//    public static int randomNumber() {
//        return new Random().nextInt((255 - 1) + 1) + 1;
//    }
}

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

import ecommerceBackend.assembler.ShoppingCartItemModelAssembler;
import ecommerceBackend.entity.Item;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.ShoppingCartItem;
import ecommerceBackend.exception.ItemNotFoundException;
import ecommerceBackend.exception.ShoppingCartItemNotFoundException;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.repository.ItemRepository;
import ecommerceBackend.repository.ShoppingCartItemRepository;
import ecommerceBackend.repository.ShoppingCartRepository;


@RestController
public class ShoppingCartItemController {
	private final ShoppingCartItemRepository repository;
	private ShoppingCartRepository scRepository;
	private ItemRepository itemRepository;
    private ShoppingCartItemModelAssembler assembler;
    
    public ShoppingCartItemController(ShoppingCartItemRepository repository, ShoppingCartItemModelAssembler assembler,
    		ShoppingCartRepository scRepository, ItemRepository itemRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.scRepository = scRepository;
        this.itemRepository = itemRepository;
    }

    // Aggregate root

    @GetMapping("/shopping-cart-items")
    public CollectionModel<EntityModel<ShoppingCartItem>> all() {

        List<EntityModel<ShoppingCartItem>> shoppingCartItems = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(shoppingCartItems, linkTo(methodOn(ShoppingCartItemController.class).all()).withSelfRel());
    }
    
    @PostMapping("/shopping-cart-item/{shopping_cart_id}/{item_id}")
    public ResponseEntity<?> newShoppingCartItem(@RequestBody ShoppingCartItem newShoppingCartItem, 
    											@PathVariable("shopping_cart_id") Long scId,
    											@PathVariable("item_id") Long itemId) {
    	
    	Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));
    	ShoppingCart sc = scRepository.findById(scId).orElseThrow(() -> new ShoppingCartNotFoundException(scId));
    	EntityModel<ShoppingCartItem> entityModel;
    	if (newShoppingCartItem.getQuantity() > item.getQuantity()) {
    		entityModel = assembler.toModel(null);
    		return ResponseEntity 
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                    .body(entityModel);
    	}
    	// if item is already in shopping cart and there is quantity in request body
    	// then only change the quantity and price of shopping cart item
    	for (int i = 0; i < sc.getItems().size(); i++) {
    		if (sc.getItems().get(i).getItemId() == itemId) {
    			// checks if quantity in request body is greater than item quantity.
    			// if it is, do nothing.
    			if (sc.getItems().get(i).getQuantity() > item.getQuantity()) {
    				entityModel = assembler.toModel(sc.getItems().get(i));
    				return ResponseEntity 
    		                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
    		                .body(entityModel);
    			}
    			sc.getItems().get(i).setQuantity(newShoppingCartItem.getQuantity());
    			sc.getItems().get(i).setPrice(item.getPrice()*sc.getItems().get(i).getQuantity());
    			entityModel = assembler.toModel(sc.getItems().get(i));
    			
    			return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    		}
    		
    	}
    	newShoppingCartItem.setShoppingCartId(sc.getId());
    	newShoppingCartItem.setItemId(item.getId());
    	newShoppingCartItem.setPrice(item.getPrice()*item.getQuantity());
    	sc.addShoppingCartItemId(newShoppingCartItem);
        entityModel = assembler.toModel(repository.save(newShoppingCartItem));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }


    // Single shoppingCartItem
    @GetMapping("/shopping-cart-items/{id}")
    public EntityModel<ShoppingCartItem> one(@PathVariable("id") Long id) {

        ShoppingCartItem shoppingCartItem = repository.findById(id) //
                .orElseThrow(() -> new ShoppingCartItemNotFoundException(id));

        return assembler.toModel(shoppingCartItem);
    }
    
    @DeleteMapping("/shopping-cart-items/{id}")
	public ResponseEntity<?> deleteShoppingCartItem(@PathVariable Long id) {
    	repository.deleteById(id);
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
//    @DeleteMapping("/employees/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
//        repository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}

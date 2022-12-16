package ecommerceBackend.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
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

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import ecommerceBackend.assembler.OrderModelAssembler;
import ecommerceBackend.assembler.ShoppingCartItemModelAssembler;
import ecommerceBackend.controller.OrderController;
import ecommerceBackend.entity.Item;
import ecommerceBackend.entity.Order;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.ShoppingCartItem;
import ecommerceBackend.entity.User;
import ecommerceBackend.exception.ItemNotFoundException;
import ecommerceBackend.exception.OrderNotFoundException;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.exception.UserNotFoundException;
import ecommerceBackend.repository.ItemRepository;
import ecommerceBackend.repository.OrderRepository;
import ecommerceBackend.repository.ShoppingCartRepository;
import ecommerceBackend.repository.UserRepository;

@Service
public class OrderService {
	
	private final OrderRepository repository;
	private final ShoppingCartRepository shoppingCartRepository;
	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final OrderModelAssembler assembler;
	
	public OrderService(OrderRepository repository, OrderModelAssembler assembler, 
			   ShoppingCartRepository shoppingCartRepository, UserRepository userRepository,
			   ItemRepository itemRepository) {
		this.repository = repository;
		this.assembler = assembler;
		this.shoppingCartRepository = shoppingCartRepository;
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
	}
	
//	@GetMapping("/orders")
    public CollectionModel<EntityModel<Order>> all() {

        List<EntityModel<Order>> orders = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }
    
//     Single item
//    @GetMapping("/orders/{id}")
    public EntityModel<Order> one(@PathVariable Long id) {

        Order order = repository.findById(id) //
                .orElseThrow(() -> new OrderNotFoundException(id));

        return assembler.toModel(order);
    }

	public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
		repository.deleteById(id);
    	return ResponseEntity.ok(null);
	}

	//id is shoppingcart id
	public ResponseEntity<?> newOrder(Order order, @PathVariable Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//		Long scId = user.getShoppingCart()
		ShoppingCart shoppingCart = user.getShoppingCart();
		
		//Check if all items that are trying to be bought are in stock
		for(ShoppingCartItem cartItem: shoppingCart.getShoppingCartItems()) {
			Long cartItemId = cartItem.getItemId();
			Item item = itemRepository.findById(cartItemId).orElseThrow(() -> new ItemNotFoundException(cartItemId));
			
			if(item.getQuantity() < cartItem.getQuantity()) {
				// THROW ERROR due to not enough stock
				return ResponseEntity //
		          .status(HttpStatus.METHOD_NOT_ALLOWED) //
		          .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
		          .body(Problem.create() //
		              .withTitle("Method not allowed") //
		              .withDetail("Insuffcient stock for item: " + item.getName()));
			}
		}
		
		// Reduce quantity from store stock
		for(ShoppingCartItem cartItem: shoppingCart.getShoppingCartItems()) {
			Long cartItemId = cartItem.getItemId();
			Item item = itemRepository.findById(cartItemId).orElseThrow(() -> new ItemNotFoundException(cartItemId));
			
			item.setQuantity(item.getQuantity() - cartItem.getQuantity());
		}
		
    	//set order from shopping cart
    	order.setShoppingCart(shoppingCart);
		user.getOrders().add(order);
		
		//clear shoppingcart from user
		shoppingCart.setShoppingCartItems(new ArrayList<ShoppingCartItem>());;
    	
		EntityModel<Order> entityModel = assembler.toModel(repository.save(order));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);

//		return ResponseEntity //
//          .status(HttpStatus.METHOD_NOT_ALLOWED) //
//          .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
//          .body(Problem.create() //
//              .withTitle("Method not allowed") //
//              .withDetail("error occured"));
		 
	}
    
//    // get items by Type
////    @GetMapping("//type/{type}")
//    public CollectionModel<EntityModel<Item>> allByType(@PathVariable String type) {
//    	List<EntityModel<Item>> items = repository.findAll()
//                .stream().filter(item -> item.getType().equals(type))
//                .map(assembler::toModel)
//                .collect(Collectors.toList());
//    	return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
//    }
    
//    // get items by Brand
////    @GetMapping("/items/brand/{brand}")
//    public CollectionModel<EntityModel<Item>> allByBrand(@PathVariable String brand) {
//    	List<EntityModel<Item>> items = repository.findAll()
//                .stream().filter(item -> item.getBrand().equals(brand))
//                .map(assembler::toModel)
//                .collect(Collectors.toList());
//    	
//    	return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
//    }
//    
//    
////    @PostMapping("/items")
//    public ResponseEntity<?> newItem(@RequestBody Item newItem) {
//
//        EntityModel<Item> entityModel = assembler.toModel(repository.save(newItem));
//
//        return ResponseEntity 
//                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
//                .body(entityModel);
//    }
//    
//    
////    @DeleteMapping("/items/{id}")
//    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
//    	repository.deleteById(id);
//    	return ResponseEntity.ok(null);
//    }
    
}

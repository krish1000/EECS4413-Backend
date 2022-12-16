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

import ecommerceBackend.assembler.OrderModelAssembler;
import ecommerceBackend.controller.OrderController;
import ecommerceBackend.entity.Order;
import ecommerceBackend.exception.OrderNotFoundException;
import ecommerceBackend.repository.OrderRepository;

@Service
public class OrderService {
	
	private final OrderRepository repository;
	private final OrderModelAssembler assembler;
	
	public OrderService(OrderRepository repository, OrderModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
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

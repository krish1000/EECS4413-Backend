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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBackend.assembler.ItemModelAssembler;
import ecommerceBackend.entity.Item;
import ecommerceBackend.exception.ItemNotFoundException;
import ecommerceBackend.repository.ItemRepository;
import ecommerceBackend.service.ItemService;

//import payroll.assembler.EmployeeModelAssembler;
//import payroll.entity.Employee;
//import payroll.exception.EmployeeNotFoundException;
//import payroll.repository.EmployeeRepository;

@RestController
public class ItemController {

	@Autowired
    private final ItemService itemService;
    
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Aggregate root

    @GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {
    	return itemService.all();
    }
    
    // Single item
    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Long id) {
    	return itemService.one(id);
    }
    
    // get items by Type
    @GetMapping("/items/type/{type}")
    public CollectionModel<EntityModel<Item>> allByType(@PathVariable String type) {
    	return itemService.allByType(type);
    }
    
    // get items by Brand
    @GetMapping("/items/brand/{brand}")
    public CollectionModel<EntityModel<Item>> allByBrand(@PathVariable String brand) {
    	return itemService.allByBrand(brand);
    }
    
    @PostMapping("/items")
    public ResponseEntity<?> newItem(@RequestBody Item newItem) {
    	return itemService.newItem(newItem);
    }
    
    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
    	return itemService.deleteItem(id);
	}
    
    // didn't do delete and put
}
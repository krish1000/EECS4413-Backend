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

import ecommerceBackend.assembler.ItemModelAssembler;
import ecommerceBackend.controller.ItemController;
import ecommerceBackend.entity.Item;
import ecommerceBackend.exception.ItemNotFoundException;
import ecommerceBackend.repository.ItemRepository;

@Service
public class ItemService {
	
	private final ItemRepository repository;
	private final ItemModelAssembler assembler;
	
	public ItemService(ItemRepository repository, ItemModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}
	
//	@GetMapping("/items")
    public CollectionModel<EntityModel<Item>> all() {

        List<EntityModel<Item>> items = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
    
//     Single item
//    @GetMapping("/items/{id}")
    public EntityModel<Item> one(@PathVariable Long id) {

        Item item = repository.findById(id) //
                .orElseThrow(() -> new ItemNotFoundException(id));

        return assembler.toModel(item);
    }
    
    // get items by Type
//    @GetMapping("/items/type/{type}")
    public CollectionModel<EntityModel<Item>> allByType(@PathVariable String type) {
    	List<EntityModel<Item>> items = repository.findAll()
                .stream().filter(item -> item.getType().equals(type))
                .map(assembler::toModel)
                .collect(Collectors.toList());
    	return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
    
    // get items by Brand
//    @GetMapping("/items/brand/{brand}")
    public CollectionModel<EntityModel<Item>> allByBrand(@PathVariable String brand) {
    	List<EntityModel<Item>> items = repository.findAll()
                .stream().filter(item -> item.getBrand().equals(brand))
                .map(assembler::toModel)
                .collect(Collectors.toList());
    	
    	return CollectionModel.of(items, linkTo(methodOn(ItemController.class).all()).withSelfRel());
    }
    
    
//    @PostMapping("/items")
    public ResponseEntity<?> newItem(@RequestBody Item newItem) {

        EntityModel<Item> entityModel = assembler.toModel(repository.save(newItem));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }
    
    
//    @DeleteMapping("/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
    	repository.deleteById(id);
    	return ResponseEntity.ok(null);
    }
    
}

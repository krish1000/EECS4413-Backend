package ecommerceBackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ecommerceBackend.assembler.ShoppingCartModelAssembler;
import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.repository.ShoppingCartRepository;


@RestController
public class ShoppingCartController {
	private final ShoppingCartRepository repository;
    private ShoppingCartModelAssembler assembler;
    
    public ShoppingCartController(ShoppingCartRepository repository, ShoppingCartModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        
    }

    // Aggregate root

    @GetMapping("/shopping-carts")
    public CollectionModel<EntityModel<ShoppingCart>> all() {

        List<EntityModel<ShoppingCart>> shoppingCarts = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(shoppingCarts, linkTo(methodOn(ShoppingCartController.class).all()).withSelfRel());
    }
    
    // we don't need this since we create a shopping cart when we create a user
    @PostMapping("/shopping-carts")
    public ResponseEntity<?> newShoppingCart(@RequestBody ShoppingCart newShoppingCart) {

        EntityModel<ShoppingCart> entityModel = assembler.toModel(repository.save(newShoppingCart));

        return ResponseEntity 
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) 
                .body(entityModel);
    }


    // Single shoppingCart
    @GetMapping("/shopping-carts/{id}")
    public EntityModel<ShoppingCart> one(@PathVariable("id") Long id) {

        ShoppingCart shoppingCart = repository.findById(id) //
                .orElseThrow(() -> new ShoppingCartNotFoundException(id));

        return assembler.toModel(shoppingCart);
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

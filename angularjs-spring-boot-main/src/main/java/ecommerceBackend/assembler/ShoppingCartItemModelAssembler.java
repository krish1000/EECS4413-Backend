package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.ShoppingCartItemController;
import ecommerceBackend.entity.ShoppingCartItem;

//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class ShoppingCartItemModelAssembler implements RepresentationModelAssembler<ShoppingCartItem, EntityModel<ShoppingCartItem>> {

    @Override
    public EntityModel<ShoppingCartItem> toModel(ShoppingCartItem shoppingCartItem) {

        return EntityModel.of(shoppingCartItem, //
                linkTo(methodOn(ShoppingCartItemController.class).one(shoppingCartItem.getId())).withSelfRel(), linkTo(methodOn(ShoppingCartItemController.class).all()).withRel("shoppingCartItems"));
    }
}

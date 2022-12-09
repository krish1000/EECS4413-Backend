package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.ShoppingCartController;
import ecommerceBackend.entity.ShoppingCart;

//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class ShoppingCartModelAssembler implements RepresentationModelAssembler<ShoppingCart, EntityModel<ShoppingCart>> {

    @Override
    public EntityModel<ShoppingCart> toModel(ShoppingCart shoppingCart) {

        return EntityModel.of(shoppingCart, //
                linkTo(methodOn(ShoppingCartController.class).one(shoppingCart.getId())).withSelfRel(), linkTo(methodOn(ShoppingCartController.class).all()).withRel("shoppingCarts"));
    }
}

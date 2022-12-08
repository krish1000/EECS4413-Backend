package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.ItemController;
import ecommerceBackend.entity.Item;

//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class ItemModelAssembler implements RepresentationModelAssembler<Item, EntityModel<Item>> {

    @Override
    public EntityModel<Item> toModel(Item item) {

        return EntityModel.of(item, //
                linkTo(methodOn(ItemController.class).one(item.getId())).withSelfRel(), linkTo(methodOn(ItemController.class).all()).withRel("items"));
    }
}

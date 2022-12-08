package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.AddressController;
import ecommerceBackend.entity.Address;


@Component
public class AddressModelAssembler implements RepresentationModelAssembler<Address, EntityModel<Address>> {

    @Override
    public EntityModel<Address> toModel(Address entity) {

        return EntityModel.of(entity, //
                linkTo(methodOn(AddressController.class).one(entity.getId())).withSelfRel(), linkTo(methodOn(AddressController.class).all()).withRel("addresses"));
    }
}

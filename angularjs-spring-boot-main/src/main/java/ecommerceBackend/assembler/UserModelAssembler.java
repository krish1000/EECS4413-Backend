package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.UserController;
import ecommerceBackend.entity.User;


//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {

        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(), linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}

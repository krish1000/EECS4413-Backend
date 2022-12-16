package ecommerceBackend.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ecommerceBackend.controller.EventController;
import ecommerceBackend.entity.Event;
import ecommerceBackend.service.EventService;


//import payroll.controller.EmployeeController;
//import payroll.entity.Employee;


@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

    @Override
    public EntityModel<Event> toModel(Event event) {

        return EntityModel.of(event, //
                linkTo(methodOn(EventService.class).getEvent(event.getId())).withSelfRel(), linkTo(methodOn(EventService.class).getAllEvents()).withRel("events"));
    }
}

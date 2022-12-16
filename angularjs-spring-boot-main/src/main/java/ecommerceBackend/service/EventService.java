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

import ecommerceBackend.assembler.EventModelAssembler;
import ecommerceBackend.controller.EventController;
import ecommerceBackend.entity.Event;
import ecommerceBackend.exception.ShoppingCartNotFoundException;
import ecommerceBackend.exception.EventNotFoundException;
import ecommerceBackend.repository.EventRepository;

@Service
public class EventService {
	
	private final EventRepository eventRepository;
	private EventModelAssembler assembler;
	
	public EventService(EventRepository eventRepository, EventModelAssembler assembler) {
		this.eventRepository = eventRepository;
		this.assembler = assembler;
	}
	
//	@GetMapping("/events")
	public CollectionModel<EntityModel<Event>> getAllEvents() {
		List<EntityModel<Event>> events = eventRepository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(events, linkTo(methodOn(EventController.class).all()).withSelfRel());
	}
	
//	@GetMapping("/events/{id}")
	public EntityModel<Event> getEvent(@PathVariable Long id) {

        Event event = eventRepository.findById(id) //
                .orElseThrow(() -> new EventNotFoundException(id));

        return assembler.toModel(event);
    }
}

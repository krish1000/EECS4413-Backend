package ecommerceBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import ecommerceBackend.entity.ShoppingCart;
import ecommerceBackend.entity.Event;

import ecommerceBackend.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Aggregate root

    @GetMapping("/events")
    public CollectionModel<EntityModel<Event>> all() {
    	return eventService.getAllEvents();
    }

    // Single event
    @GetMapping("/events/{id}")
    public EntityModel<Event> one(@PathVariable Long id) {
    	return eventService.getEvent(id);
    }

//
}

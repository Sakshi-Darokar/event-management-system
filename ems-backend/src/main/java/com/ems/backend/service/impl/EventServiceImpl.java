package com.ems.backend.service.impl;


import com.ems.backend.dto.EventRequest;
import com.ems.backend.dto.EventResponse;
import com.ems.backend.entity.Event;
import com.ems.backend.repository.EventRepository;
import com.ems.backend.service.EventService;
import org.springframework.stereotype.Service;
import com.ems.backend.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private EventResponse mapToResponse(Event event) {
        return new EventResponse(
                event.getId(),
                event.getEventName(),
                event.getDescription(),
                event.getDateTime(),
                event.getLocation(),
                event.getPrice(),
                event.getTotalSeats(),
                event.getAvailableSeats(),
                event.getCategory()
        );
    }

    @Override
    public EventResponse createEvent(EventRequest request) {

        Event event = new Event();
        event.setEventName(request.getEventName());
        event.setDescription(request.getDescription());
        event.setDateTime(request.getDateTime());
        event.setLocation(request.getLocation());
        event.setPrice(request.getPrice());
        event.setTotalSeats(request.getTotalSeats());
        event.setAvailableSeats(request.getTotalSeats()); // IMPORTANT
        event.setCategory(request.getCategory());

        Event saved = eventRepository.save(event);

        return mapToResponse(saved);
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest request) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));


        event.setEventName(request.getEventName());
        event.setDescription(request.getDescription());
        event.setDateTime(request.getDateTime());
        event.setLocation(request.getLocation());
        event.setPrice(request.getPrice());
        event.setCategory(request.getCategory());

        // seats update logic
        int bookedSeats = event.getTotalSeats() - event.getAvailableSeats();
        event.setTotalSeats(request.getTotalSeats());
        event.setAvailableSeats(request.getTotalSeats() - bookedSeats);

        Event updated = eventRepository.save(event);

        return mapToResponse(updated);
    }

    @Override
    public String deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));


        eventRepository.delete(event);

        return "Event deleted successfully";
    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EventResponse getEventById(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + id));


        return mapToResponse(event);
    }
}

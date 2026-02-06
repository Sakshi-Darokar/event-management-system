package com.ems.backend.service;

import com.ems.backend.dto.EventRequest;
import com.ems.backend.dto.EventResponse;

import java.util.List;

public interface EventService {

    EventResponse createEvent(EventRequest request);

    EventResponse updateEvent(Long id, EventRequest request);

    String deleteEvent(Long id);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(Long id);
}

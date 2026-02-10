package com.ems.backend.controller;

import com.ems.backend.repository.BookingRepository;
import com.ems.backend.repository.EventRepository;
import com.ems.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public AdminController(
            UserRepository userRepository,
            EventRepository eventRepository,
            BookingRepository bookingRepository
    ) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    // ✅ API 1: DASHBOARD (Counts)
    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        return ResponseEntity.ok(
                Map.of(
                        "totalUsers", userRepository.count(),
                        "totalEvents", eventRepository.count(),
                        "totalBookings", bookingRepository.count()
                )
        );
    }

    // ✅ API 2: VIEW ALL EVENTS
    @GetMapping("/events")
    public ResponseEntity<?> allEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    // ✅ API 3: VIEW ALL USERS
    @GetMapping("/users")
    public ResponseEntity<?> allUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // ✅ API 4: BOOKINGS PER EVENT (COUNT)
    @GetMapping("/bookings")
    public ResponseEntity<?> bookingsPerEvent() {
        List<Object[]> data = bookingRepository.countBookingsPerEvent();

        List<Map<String, Object>> response = data.stream()
                .map(obj -> Map.of(
                        "eventName", obj[0],
                        "totalBookings", obj[1]
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}

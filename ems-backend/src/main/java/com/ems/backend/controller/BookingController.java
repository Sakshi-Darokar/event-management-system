package com.ems.backend.controller;

import com.ems.backend.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam Long userId,
            @RequestParam Long eventId) {

        return ResponseEntity.ok(
                bookingService.registerForEvent(userId, eventId)
        );
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancel(
            @RequestParam Long userId,
            @RequestParam Long eventId) {

        return ResponseEntity.ok(
                bookingService.cancelBooking(userId, eventId)
        );
    }
}

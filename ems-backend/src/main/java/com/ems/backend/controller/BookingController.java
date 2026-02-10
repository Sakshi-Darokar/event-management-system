package com.ems.backend.controller;

import com.ems.backend.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
            Authentication authentication,
            @RequestParam Long eventId
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(
                bookingService.registerForEvent(email, eventId)
        );
    }


    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancel(
            Authentication authentication,
            @RequestParam Long eventId
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(
                bookingService.cancelBooking(email, eventId)
        );
    }
}

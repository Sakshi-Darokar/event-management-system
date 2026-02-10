package com.ems.backend.service.impl;

import com.ems.backend.entity.*;
import com.ems.backend.repository.*;
import com.ems.backend.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public String registerForEvent(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Duplicate booking check
        if (bookingRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new RuntimeException("You already booked this event");
        }

        // Seat availability check
        if (event.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // Reduce seat
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setBookingDate(LocalDateTime.now());

        bookingRepository.save(booking);

        return "Event booked successfully";
    }

    @Override
    public String cancelBooking(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Booking booking = bookingRepository
                .findByUserAndEvent(user, event)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.delete(booking);

        // Increase seat
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepository.save(event);

        return "Booking cancelled successfully";
    }
}

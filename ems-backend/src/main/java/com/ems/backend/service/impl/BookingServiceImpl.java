package com.ems.backend.service.impl;

import com.ems.backend.entity.Booking;
import com.ems.backend.entity.Event;
import com.ems.backend.entity.User;
import com.ems.backend.repository.BookingRepository;
import com.ems.backend.repository.EventRepository;
import com.ems.backend.repository.UserRepository;
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
    public String registerForEvent(String email, Long eventId) {

        // 1️⃣ Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Find event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 3️⃣ Duplicate booking check
        if (bookingRepository.findByUserAndEvent(user, event).isPresent()) {
            throw new RuntimeException("You already booked this event");
        }

        // 4️⃣ Seat availability check
        if (event.getAvailableSeats() <= 0) {
            throw new RuntimeException("No seats available");
        }

        // 5️⃣ Reduce seat
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        // 6️⃣ Create booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setBookingDate(LocalDateTime.now());

        bookingRepository.save(booking);

        return "Event booked successfully";



    }

    @Override
    public String cancelBooking(String email, Long eventId) {

        // 1️⃣ Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2️⃣ Find event
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // 3️⃣ Find booking
        Booking booking = bookingRepository
                .findByUserAndEvent(user, event)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // 4️⃣ Delete booking
        bookingRepository.delete(booking);

        // 5️⃣ Increase seat back
        event.setAvailableSeats(event.getAvailableSeats() + 1);
        eventRepository.save(event);

        return "Booking cancelled successfully";
    }
}

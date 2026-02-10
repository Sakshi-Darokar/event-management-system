package com.ems.backend.service.impl;

import com.ems.backend.dto.UpdateUserRequest;
import com.ems.backend.dto.UserProfileResponse;
import com.ems.backend.entity.Booking;
import com.ems.backend.entity.Event;
import com.ems.backend.entity.User;
import com.ems.backend.repository.BookingRepository;
import com.ems.backend.repository.UserRepository;
import com.ems.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public UserProfileResponse getProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserProfileResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    @Override
    public UserProfileResponse updateProfileByEmail(String email, UpdateUserRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getName() != null && !request.getName().isBlank()) {
            user.setName(request.getName());
        }

        userRepository.save(user);
        return new UserProfileResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    @Override
    public List<Event> getMyEventsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Booking> bookings = bookingRepository.findByUser(user);
        return bookings.stream().map(Booking::getEvent).toList();
    }
}

package com.ems.backend.service;

public interface BookingService {

    String registerForEvent(Long userId, Long eventId);

    String cancelBooking(Long userId, Long eventId);
}

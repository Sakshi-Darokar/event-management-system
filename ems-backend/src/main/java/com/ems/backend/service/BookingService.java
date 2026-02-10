package com.ems.backend.service;

public interface BookingService {

    String registerForEvent(String email, Long eventId);

    String cancelBooking(String email, Long eventId);
}

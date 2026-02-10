package com.ems.backend.repository;

import com.ems.backend.entity.Booking;
import com.ems.backend.entity.Event;
import com.ems.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {


    List<Booking> findByUser(User user);


    Optional<Booking> findByUserAndEvent(User user, Event event);



}

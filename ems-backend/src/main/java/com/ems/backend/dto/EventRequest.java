package com.ems.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventRequest {
    private String eventName;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private double price;
    private int totalSeats;
    private String category;
}

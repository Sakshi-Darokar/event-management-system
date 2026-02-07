package com.ems.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EventResponse {
    private Long id;
    private String eventName;
    private String description;
    private LocalDateTime dateTime;
    private String location;
    private double price;
    private int totalSeats;
    private int availableSeats;
    private String category;
}

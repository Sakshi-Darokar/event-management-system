package com.ems.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;

    @Column(length = 1000)
    private String description;

    private LocalDateTime dateTime;

    private String location;

    private double price;

    private int totalSeats;

    private int availableSeats;

    private String category;
}

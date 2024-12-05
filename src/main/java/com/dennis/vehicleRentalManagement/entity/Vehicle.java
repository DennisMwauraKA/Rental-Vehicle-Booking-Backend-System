package com.dennis.vehicleRentalManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity

public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String model;
    private String color;
    private Double hiringPrice;
    private Integer seatCapacity;
    private String fuelCapacity;
    private String fuelType;
    private String engineType;
    private boolean available;
}

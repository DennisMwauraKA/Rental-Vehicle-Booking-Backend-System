package com.dennis.vehicleRentalManagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle {
    @Id
    private Integer id;
    private String model;
    private String color;
    private Double hiringPrice;
    private String horsePower;
    private String fuelCapacity;
    private String fuelType;
    private String engineType;
    private boolean available;
}

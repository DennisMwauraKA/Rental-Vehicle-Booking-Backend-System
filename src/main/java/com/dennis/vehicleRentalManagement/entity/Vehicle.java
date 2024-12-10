package com.dennis.vehicleRentalManagement.entity;

import com.dennis.vehicleRentalManagement.commonfields.ParentEntity;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity

public class Vehicle extends ParentEntity {

    private String model;
    private String color;
    private Double hiringPrice;
    private Integer seatCapacity;
    private String fuelCapacity;
    private String fuelType;
    private String engineType;
    private boolean available;
}

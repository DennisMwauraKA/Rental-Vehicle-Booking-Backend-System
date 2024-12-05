package com.dennis.vehicleRentalManagement.service;

import com.dennis.vehicleRentalManagement.dtos.VehicleRegistrationRequest;
import com.dennis.vehicleRentalManagement.entity.Vehicle;
import org.springframework.stereotype.Service;

@Service
public class VehicleMapper {

    public Vehicle toVehicle(VehicleRegistrationRequest request){

        return Vehicle.builder()

                .id(request.id())
                .model(request.model())
                .color(request.color())
                .hiringPrice(request.hiringPrice())
                .seatCapacity(request.seatCapacity())
                .fuelCapacity(request.fuelCapacity())
                .fuelType(request.fuelType())
                .engineType(request.engineType())
                .available(true)



                .build();
    }


}

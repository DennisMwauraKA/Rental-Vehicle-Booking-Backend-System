package com.dennis.vehicleRentalManagement.service;

import com.dennis.vehicleRentalManagement.commonfields.PageResponse;
import com.dennis.vehicleRentalManagement.dtos.VehicleRegistrationRequest;
import com.dennis.vehicleRentalManagement.dtos.VehicleResponse;
import com.dennis.vehicleRentalManagement.entity.User;
import com.dennis.vehicleRentalManagement.entity.Vehicle;
import com.dennis.vehicleRentalManagement.repository.VehicleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    // register vehicles to the database
    public Integer registerVehicle(VehicleRegistrationRequest request) {

        try{
            Vehicle vehicle = vehicleMapper.toVehicle(request);
            return vehicleRepository.save(vehicle).getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // remove a specific vehicle from the database
    public Integer deleteVehicle(Integer vehicleId) {
        // find if vehicle specified existsById and delete
        VehicleRepository vehicleRepository = this.vehicleRepository;

        try {
            if (vehicleRepository.existsById(vehicleId)) {
                vehicleRepository.deleteById(vehicleId);
                return vehicleId;

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public PageResponse<VehicleResponse> findAllVehicles(int page, int size)  {


    }
}

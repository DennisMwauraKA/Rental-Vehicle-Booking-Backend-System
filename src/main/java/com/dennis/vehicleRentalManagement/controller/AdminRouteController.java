package com.dennis.vehicleRentalManagement.controller;


import com.dennis.vehicleRentalManagement.commonfields.PageResponse;
import com.dennis.vehicleRentalManagement.dtos.UpdateVehicleDto;
import com.dennis.vehicleRentalManagement.dtos.UsersRequestDto;
import com.dennis.vehicleRentalManagement.dtos.VehicleRegistrationRequest;
import com.dennis.vehicleRentalManagement.entity.User;
import com.dennis.vehicleRentalManagement.entity.Vehicle;
import com.dennis.vehicleRentalManagement.service.UsersService;
import com.dennis.vehicleRentalManagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminRouteController {

    private final VehicleService vehicleService;
    private final UsersService usersService;

    public AdminRouteController(VehicleService vehicleService, UsersService usersService) {
        this.vehicleService = vehicleService;
        this.usersService = usersService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "Get :Admin";
    }

    // register vehicles to the database
    @PostMapping("/register-vehicle")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Integer> registerVehicle(@RequestBody @Valid VehicleRegistrationRequest request) {
        return ResponseEntity.ok(vehicleService.registerVehicle(request));
    }

    // delete vehicles from the database
    @DeleteMapping("/delete-vehicle/{vehicle-id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteVehicle(@PathVariable("vehicle-id") Integer vehicleId) {

        return ResponseEntity.ok(vehicleService.deleteVehicle(vehicleId));

    }

    // view all users in the system
    @GetMapping("/view-users")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<PageResponse<UsersRequestDto>> viewAllUsers(
            @RequestParam(name = "page",defaultValue = "0",required = false)int page,
            @RequestParam(name = "size",defaultValue = "10",required = false)int size

    ) {
        return ResponseEntity.ok(usersService.viewAllUsers(page,size));
    }

    // update a vehicle
    @PutMapping("/update-vehicle/{vehicle-id}")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("vehicle-id") Integer vehicleId,
                                                 @RequestBody @Valid UpdateVehicleDto updateVehicleDto) {

        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId,updateVehicleDto));
    }


}

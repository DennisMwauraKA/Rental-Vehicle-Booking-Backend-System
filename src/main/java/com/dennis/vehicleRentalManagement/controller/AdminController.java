package com.dennis.vehicleRentalManagement.controller;


import com.dennis.vehicleRentalManagement.commonfields.PageResponse;
import com.dennis.vehicleRentalManagement.dtos.VehicleRegistrationRequest;
import com.dennis.vehicleRentalManagement.dtos.VehicleResponse;
import com.dennis.vehicleRentalManagement.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final VehicleService vehicleService;

    public AdminController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
    public String get() {
        return "Get :Admin";
    }


    @PostMapping("/register-vehicle")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Integer> registerVehicle(@RequestBody @Valid VehicleRegistrationRequest request) {
        return ResponseEntity.ok(vehicleService.registerVehicle(request));
    }


    @DeleteMapping("/delete-vehicle/{vehicle-id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<Integer> deleteVehicle(@PathVariable("vehicle-id") Integer vehicleId) {

        return ResponseEntity.ok(vehicleService.deleteVehicle(vehicleId));

    }


    @GetMapping("/vehicles-listing")
    @PreAuthorize("hasAnyAuthority('admin:read','user:read')")

    public ResponseEntity<PageResponse<VehicleResponse>> findAllVehicles(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                                         @RequestParam(name = "size", defaultValue = "0", required = false) int size)
    {
return ResponseEntity.ok(vehicleService.findAllVehicles(page,size));
    }


}

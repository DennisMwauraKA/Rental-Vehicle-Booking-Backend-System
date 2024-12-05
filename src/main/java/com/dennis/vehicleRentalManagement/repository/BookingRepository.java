package com.dennis.vehicleRentalManagement.repository;

import com.dennis.vehicleRentalManagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

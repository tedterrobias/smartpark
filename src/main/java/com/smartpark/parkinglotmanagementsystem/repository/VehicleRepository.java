package com.smartpark.parkinglotmanagementsystem.repository;

import com.smartpark.parkinglotmanagementsystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>
{
    List<Vehicle> findByParkingLotLotIdAndActiveTrue(Long parkingLotId);

    Optional<Vehicle> findByLicensePlateAndActiveTrue(String licensePlate);
}

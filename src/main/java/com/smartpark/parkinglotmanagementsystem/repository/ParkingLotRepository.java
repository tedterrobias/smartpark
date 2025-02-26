package com.smartpark.parkinglotmanagementsystem.repository;

import com.smartpark.parkinglotmanagementsystem.model.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>
{
        Optional<ParkingLot> findByLocation(String location);
}

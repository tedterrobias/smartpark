package com.smartpark.parkinglotmanagementsystem.service;

import com.smartpark.parkinglotmanagementsystem.dto.VehicleDto;
import com.smartpark.parkinglotmanagementsystem.exception.FullParkingLotException;
import com.smartpark.parkinglotmanagementsystem.exception.NotFoundException;
import com.smartpark.parkinglotmanagementsystem.mapper.VehicleMapper;
import com.smartpark.parkinglotmanagementsystem.model.ParkingLot;
import com.smartpark.parkinglotmanagementsystem.model.Vehicle;
import com.smartpark.parkinglotmanagementsystem.repository.ParkingLotRepository;
import com.smartpark.parkinglotmanagementsystem.repository.VehicleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService
{
    private final ParkingLotRepository parkingLotRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Transactional
    public VehicleDto registerVehicle(VehicleDto vehicleDto, Long lotId)
        throws NotFoundException, FullParkingLotException
    {
        ParkingLot parkingLot = parkingLotRepository.findById(lotId)
            .orElseThrow(() -> new NotFoundException("Parking lot not found with ID: " + lotId));

        if (parkingLot.getOccupied() >= parkingLot.getCapacity())
        {
            throw new FullParkingLotException(String.format(
                "Parking Lot %s is already full.", parkingLot.getLocation()));
        }

        parkingLot.setOccupied(parkingLot.getOccupied() + 1);
        ParkingLot updatedParkingLot = parkingLotRepository.save(parkingLot);

        Vehicle vehicle = vehicleMapper.fromDto(vehicleDto);
        vehicle.setParkingLot(updatedParkingLot);

        return Optional.of(vehicleRepository.save(vehicle))
            .map(vehicleMapper::toDto)
            .orElse(null);
    }

    @Transactional
    public VehicleDto checkOutVehicle(String licensePlate)
    {
        return vehicleRepository.findByLicensePlateAndActiveTrue(licensePlate)
            .map(vehicle -> {
                vehicle.setActive(false);

                ParkingLot parkingLot = vehicle.getParkingLot();
                parkingLot.setOccupied(parkingLot.getOccupied() - 1);
                parkingLotRepository.save(parkingLot);

                return vehicle;
            })
            .map(vehicleRepository::save)
            .map(vehicleMapper::toDto)
            .orElse(null);
    }

    public List<VehicleDto> getAllCurrentlyParkedVehiclesByParkingLot(Long lotId)
    {
        return vehicleRepository.findByParkingLotLotIdAndActiveTrue(lotId)
        .stream()
        .map(vehicleMapper::toDto)
        .toList();
    }

    public List<VehicleDto> getAllVehicles()
    {
        return vehicleRepository.findAll()
            .stream()
            .map(vehicleMapper::toDto)
            .toList();
    }
}

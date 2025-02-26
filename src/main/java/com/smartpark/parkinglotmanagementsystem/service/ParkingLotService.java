package com.smartpark.parkinglotmanagementsystem.service;

import com.smartpark.parkinglotmanagementsystem.dto.ParkingLotDto;
import com.smartpark.parkinglotmanagementsystem.exception.NotFoundException;
import com.smartpark.parkinglotmanagementsystem.mapper.ParkingLotMapper;
import com.smartpark.parkinglotmanagementsystem.model.ParkingLot;
import com.smartpark.parkinglotmanagementsystem.repository.ParkingLotRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotService
{
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingLotMapper parkingLotMapper;

    @Transactional
    public ParkingLotDto registerParkingLot(ParkingLotDto parkingLotDto)
    {
        return parkingLotRepository.findByLocation(parkingLotDto.getLocation())
            .map(parkingLotMapper::toDto)
            .orElseGet(() -> Optional.of(parkingLotRepository.save(parkingLotMapper.fromDto(parkingLotDto)))
                .map(parkingLotMapper::toDto)
                .orElse(null));
    }

    public String getAvailableParkingSpaces(Long lotId) throws NotFoundException
    {
        ParkingLot parkingLot = parkingLotRepository.findById(lotId)
            .orElseThrow(() -> new NotFoundException("Parking Lot Not Found"));

        return String.format("Parking lot %s has %d capacity and %d occupied right now.",
            parkingLot.getLocation(), parkingLot.getCapacity(), parkingLot.getOccupied());
    }
}

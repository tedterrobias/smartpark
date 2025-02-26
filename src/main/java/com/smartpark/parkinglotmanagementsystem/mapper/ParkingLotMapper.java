package com.smartpark.parkinglotmanagementsystem.mapper;

import com.smartpark.parkinglotmanagementsystem.dto.ParkingLotDto;
import com.smartpark.parkinglotmanagementsystem.model.ParkingLot;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParkingLotMapper
{
    ParkingLotDto toDto(ParkingLot parkingLot);

    ParkingLot fromDto(ParkingLotDto parkingLotDto);
}

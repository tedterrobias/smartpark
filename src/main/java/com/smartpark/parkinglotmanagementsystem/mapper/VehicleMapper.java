package com.smartpark.parkinglotmanagementsystem.mapper;

import com.smartpark.parkinglotmanagementsystem.dto.VehicleDto;
import com.smartpark.parkinglotmanagementsystem.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper
{
    VehicleDto toDto(Vehicle vehicle);

    Vehicle fromDto(VehicleDto vehicleDto);
}

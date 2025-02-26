package com.smartpark.parkinglotmanagementsystem.dto;

import com.smartpark.parkinglotmanagementsystem.enumeration.VehicleType;
import lombok.Data;

@Data
public class VehicleDto
{
    private String licensePlate;
    private VehicleType vehicleType;
    private String ownerName;
    private boolean active;
}

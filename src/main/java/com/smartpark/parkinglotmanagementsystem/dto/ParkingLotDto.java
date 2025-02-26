package com.smartpark.parkinglotmanagementsystem.dto;

import lombok.Data;

@Data
public class ParkingLotDto
{
    private String location;
    private int capacity;
    private int occupied;
}

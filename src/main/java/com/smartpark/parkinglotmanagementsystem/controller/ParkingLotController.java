package com.smartpark.parkinglotmanagementsystem.controller;

import com.smartpark.parkinglotmanagementsystem.dto.ParkingLotDto;
import com.smartpark.parkinglotmanagementsystem.exception.NotFoundException;
import com.smartpark.parkinglotmanagementsystem.service.ParkingLotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parkinglot")
public class ParkingLotController
{

    private final ParkingLotService parkingLotService;

    @Operation(summary = "Create a parking lot",
        description = "Create a parking lot. lotId is incremental and sequential. Starts at 1.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParkingLotDto> createParkingLot(@RequestBody @Valid ParkingLotDto parkingLotDto) {
        ParkingLotDto createdParkingLot = parkingLotService.registerParkingLot(parkingLotDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdParkingLot);
    }

    @Operation(summary = "get available parking slots parking lot",
        description = "get available parking slots parking lot. lotId is incremental and sequential. Starts at 1.")
    @GetMapping("/available")
    public ResponseEntity<String> getAvailableParkingSpaces(@RequestParam Long lotId) throws NotFoundException
    {
        String availableSpaces = parkingLotService.getAvailableParkingSpaces(lotId);
        return ResponseEntity.ok(availableSpaces);
    }
}

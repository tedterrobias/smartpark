package com.smartpark.parkinglotmanagementsystem.controller;

import com.smartpark.parkinglotmanagementsystem.dto.VehicleDto;
import com.smartpark.parkinglotmanagementsystem.exception.FullParkingLotException;
import com.smartpark.parkinglotmanagementsystem.exception.NotFoundException;
import com.smartpark.parkinglotmanagementsystem.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vehicle")
public class VehicleController
{
    private final VehicleService vehicleService;

    @Operation(summary = "Register and check in a new vehicle",
        description = "Registers a vehicle in the specified parking lot.")
    @PostMapping(value = "/{lotId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDto> registerVehicle(@RequestBody @Valid VehicleDto vehicleDto, @PathVariable Long lotId)
        throws NotFoundException, FullParkingLotException
    {
        VehicleDto registeredVehicle = vehicleService.registerVehicle(vehicleDto, lotId);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredVehicle);
    }

    @Operation(summary = "Checkout a vehicle",
        description = "Checkouts a vehicle in the specified parking lot.")
    @PostMapping("/checkout")
    public ResponseEntity<VehicleDto> checkOutVehicle(@RequestParam String licensePlate) {
        VehicleDto checkedOutVehicle = vehicleService.checkOutVehicle(licensePlate);
        return ResponseEntity.ok(checkedOutVehicle);
    }

    @Operation(summary = "get all currently parked vehicles",
        description = "get all currently parked vehicles in the specified parking lot.")
    @GetMapping("/bulk/active")
    public ResponseEntity<List<VehicleDto>> getAllCurrentlyParkedVehiclesByParkingLot(@RequestParam Long lotId) {
        List<VehicleDto> parkedVehicles = vehicleService.getAllCurrentlyParkedVehiclesByParkingLot(lotId);
        return ResponseEntity.ok(parkedVehicles);
    }
}

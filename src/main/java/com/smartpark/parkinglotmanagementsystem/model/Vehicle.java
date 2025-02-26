package com.smartpark.parkinglotmanagementsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartpark.parkinglotmanagementsystem.enumeration.VehicleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "vehicle_id", updatable = false, nullable = false)
    private Long vehicleId;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @NotBlank(message = "Owner name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Owner name must contain only letters and spaces")
    private String ownerName;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", nullable = false)
    @JsonIgnore
    private ParkingLot parkingLot;
}

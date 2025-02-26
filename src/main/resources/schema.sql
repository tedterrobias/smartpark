CREATE TABLE parking_lots (
    lot_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    occupied INT NOT NULL
);

CREATE TABLE vehicles (
    vehicle_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    license_plate VARCHAR(255) NOT NULL,
    vehicle_type VARCHAR(255) NOT NULL,
    owner_name VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    parking_lot_id BIGINT NOT NULL,
    CONSTRAINT fk_parking_lot FOREIGN KEY (parking_lot_id) REFERENCES parking_lots (lot_id) ON DELETE CASCADE
);

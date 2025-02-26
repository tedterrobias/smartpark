# SmartPark Parking Lot Management Application

## Building and Running the App 

### On terminal, go to project path and execute the following
- `mvn clean package`
- `java -jar target/`< JarFileGeneratedInTargetFolder >`.jar `
  - e.g. `java -jar target/parkinglotmanagementsystem-0.0.1-SNAPSHOT.jar`

### Testing The App

- go to `localhost:9090` for the swagger UI

## ParkingLot Entity Structure
- `lotId:Long` - ID (Auto-generated, numeric, sequential, **starts with 1, increments by 1**)
- `location:String`
- `capacity:int`
- `occupied:int`

## Vehicle Entity Structure
- `vehicleId:Long` - ID (Auto-generated, numeric, sequential, **starts with 1, increments by 1**)
- `licensePlate:String`
- `vehicleType:VehicleType` (enum: `CAR, MOTORCYCLE, TRUCK`)
- `ownerName:String`
- `active:boolean` -(flag to identify if vehicle is currently parked or not)
- `parkingLot:ParkingLot` - (to identify which parking lot the vehicle is parked)

## Initial Settings

The tables are preloaded upon running the application.
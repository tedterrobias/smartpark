# SmartPark Parking Lot Management Application

## Building and Running the App 

### Go to project path and execute the following
- mvn clean package
- java -jar target/< JarFileGeneratedInTargetFolder >.jar
- - parkinglotmanagementsystem-0.0.1-SNAPSHOT.jar

### Testing The App

- go to localhost:9090 for the swagger UI

## ParkingLot Entity Structure
- lotId:Long - ID
- location:String
- capacity:int
- occupied:int

## Vehicle Entity Structure
- vehicleId:Long - ID
- licensePlate:String
- vehicleType:VehicleType (enum: CAR, MOTORCYCLE, TRUCK)
- ownerName:String
- active:boolean -(flag to identify if vehicle is currently parked or not)
- parkingLot:ParkingLot - (to identify which parking lot the vehicle is parked)

## Initial Settings

The tables are preloaded upon running the application.

    



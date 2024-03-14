package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Vehicle;
import machine_coding.parking_lot.models.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class VehicleRepository {

    private static int ID = 1;
    private Map<Integer, Vehicle> vehicleDB;

    public VehicleRepository() {
        this.vehicleDB = new HashMap<>();
    }

    public VehicleRepository(Map<Integer, Vehicle> data) {
        this.vehicleDB = data;
    }

    public Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType) {
        // Check if Vehicle already exists or not, if exists return the Vehicle
        for(Map.Entry<Integer, Vehicle> entry: vehicleDB.entrySet()) {
            Vehicle vehicle = entry.getValue();
            if(vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return vehicle;
            }
        }
        // If vehicle NOT exists, then create a new vehicle and add it to DB
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehicleType);
        vehicle.setId(ID);
        vehicleDB.put(ID++, vehicle);
        return vehicle;
    }

}

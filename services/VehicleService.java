package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Vehicle;
import machine_coding.parking_lot.models.VehicleType;

public interface VehicleService {

    Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType);

}

package machine_coding.parking_lot.strategies.spot_assignment;

import machine_coding.parking_lot.exceptions.NoSpotsAvailableException;
import machine_coding.parking_lot.models.ParkingLot;
import machine_coding.parking_lot.models.Spot;
import machine_coding.parking_lot.models.VehicleType;

public interface AssignSpotStrategy {

    Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotsAvailableException;

}

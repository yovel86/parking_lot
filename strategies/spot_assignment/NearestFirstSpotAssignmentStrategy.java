package machine_coding.parking_lot.strategies.spot_assignment;

import machine_coding.parking_lot.exceptions.NoSpotsAvailableException;
import machine_coding.parking_lot.models.*;

public class NearestFirstSpotAssignmentStrategy implements AssignSpotStrategy {

    @Override
    public Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotsAvailableException {
        for(Floor floor: parkingLot.getFloors()) {
            if(floor.getFloorStatus().equals(FloorStatus.OPERATIONAL)) {
                for(Section section: floor.getSections()) {
                    for(Spot spot: section.getSpots()) {
                        if(spot.getVehicleType().equals(vehicleType) && spot.getSpotStatus().equals(SpotStatus.UNOCCUPIED)) {
                            spot.setSpotStatus(SpotStatus.OCCUPIED);
                            return spot;
                        }
                    }
                }
            }
        }
        throw new NoSpotsAvailableException("No spots available for " + vehicleType.toString());
    }

}

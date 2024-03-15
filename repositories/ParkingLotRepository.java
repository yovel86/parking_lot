package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Gate;
import machine_coding.parking_lot.models.ParkingLot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotRepository {

    Map<Integer, ParkingLot> parkingLotDB;

    public ParkingLotRepository() {
        this.parkingLotDB = new HashMap<>();
    }

    public ParkingLotRepository(Map<Integer, ParkingLot> data) {
        this.parkingLotDB = data;
    }

    public ParkingLot getParkingLotByGateId(int gateId) {
        for(Map.Entry<Integer, ParkingLot> entry: parkingLotDB.entrySet()) {
            ParkingLot parkingLot = entry.getValue();
            for(Gate gate: parkingLot.getGates()) {
                if(gate.getId() == gateId) {
                    return parkingLot;
                }
            }
        }
        return null;
    }

}

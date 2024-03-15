package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.ParkingLot;

public interface ParkingLotService {

    ParkingLot getParkingLotByGateId(int gateId);

}

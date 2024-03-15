package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.ParkingLot;
import machine_coding.parking_lot.repositories.ParkingLotRepository;

public class ParkingLotServiceImpl implements ParkingLotService {

    ParkingLotRepository parkingLotRepository;

    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot getParkingLotByGateId(int gateId) {
        return parkingLotRepository.getParkingLotByGateId(gateId);
    }

}

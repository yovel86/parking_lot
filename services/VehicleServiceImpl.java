package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Vehicle;
import machine_coding.parking_lot.models.VehicleType;
import machine_coding.parking_lot.repositories.VehicleRepository;

public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle createIfNotExists(String vehicleNumber, VehicleType vehicleType) {
        return vehicleRepository.createIfNotExists(vehicleNumber, vehicleType);
    }

}

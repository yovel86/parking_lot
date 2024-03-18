package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Slab;
import machine_coding.parking_lot.models.VehicleType;

import java.util.List;

public interface SlabService {

    List<Slab> getSlabsByVehicleType(VehicleType vehicleType);

}

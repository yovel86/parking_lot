package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Slab;
import machine_coding.parking_lot.models.VehicleType;
import machine_coding.parking_lot.repositories.SlabRepository;

import java.util.List;

public class SlabServiceImpl implements SlabService {

    private SlabRepository slabRepository;

    public SlabServiceImpl(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType) {
        return slabRepository.getSlabsByVehicleType(vehicleType);
    }

}

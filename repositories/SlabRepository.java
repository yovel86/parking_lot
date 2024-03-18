package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Slab;
import machine_coding.parking_lot.models.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {

    private Map<Integer, Slab> slabDB;

    public SlabRepository() {
        this.slabDB = new HashMap<>();
    }

    public SlabRepository(Map<Integer, Slab> slabDB) {
        this.slabDB = slabDB;
    }

    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType) {
        List<Slab> slabs = new ArrayList<>();
        for (Map.Entry<Integer, Slab> entry : slabDB.entrySet()) {
            Slab slab = entry.getValue();
            if(slab.getVehicleType().equals(vehicleType)) slabs.add(slab);
        }
        slabs.sort((slab1, slab2) -> slab1.getStartHour() - slab2.getStartHour());
        return slabs;
    }

}

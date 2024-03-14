package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Gate;

import java.util.HashMap;
import java.util.Map;

public class GateRepository {

    private Map<Integer, Gate> gateDB;

    public GateRepository() {
        this.gateDB = new HashMap<>();
    }

    public GateRepository(Map<Integer, Gate> data) {
        this.gateDB = data;
    }

    public Gate getGateById(int gateId) {
        return gateDB.get(gateId);
    }

}

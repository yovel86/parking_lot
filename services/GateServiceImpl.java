package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Gate;
import machine_coding.parking_lot.repositories.GateRepository;

public class GateServiceImpl implements GateService {

    private GateRepository gateRepository = new GateRepository();

    @Override
    public Gate getGateById(int gateId) {
        return gateRepository.getGateById(gateId);
    }

}

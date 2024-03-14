package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Gate;
import machine_coding.parking_lot.models.Ticket;
import machine_coding.parking_lot.models.Vehicle;
import machine_coding.parking_lot.models.VehicleType;

public class TicketServiceImpl implements TicketService {

    private GateService gateService;
    private VehicleService vehicleService;

    public TicketServiceImpl(GateService gateService, VehicleService vehicleService) {
        this.gateService = gateService;
        this.vehicleService = vehicleService;
    }

    @Override
    public Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) {
        /*
            Need to create a Ticket obj, store it in DB & return
            Ticket obj contains - Gate, Vehicle, AssignedSpot, EntryTime
            1. Using gateId, get the "Gate" obj
            2. For creating "Vehicle" obj, there are 2 scenarios (createIfNotExists)
                    a). If a new Vehicle comes, we need to create a new Vehicle, store it in DB and retrieve
                    b). If the vehicle already exists in DB, then simply retrieve the Vehicle from DB
            3. Use Strategy pattern to find an empty spot - "AssignedSpot"
            4. Can easily get the "EntryTime" using the Date obj
        */
        Gate entryGate = gateService.getGateById(gateId);
        VehicleType type = VehicleType.getVehicleTypeFromString(vehicleType);
        Vehicle vehicle = vehicleService.createIfNotExists(vehicleNumber, type);
        return null;
    }

}

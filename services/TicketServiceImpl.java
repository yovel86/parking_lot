package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.TicketRepository;
import machine_coding.parking_lot.strategies.spot_assignment.AssignSpotStrategy;

import java.util.Date;

public class TicketServiceImpl implements TicketService {

    private GateService gateService;
    private VehicleService vehicleService;
    private ParkingLotService parkingLotService;
    private TicketRepository ticketRepository;
    private AssignSpotStrategy assignSpotStrategy;

    public TicketServiceImpl(GateService gateService, VehicleService vehicleService, ParkingLotService parkingLotService, TicketRepository ticketRepository, AssignSpotStrategy assignSpotStrategy) {
        this.gateService = gateService;
        this.vehicleService = vehicleService;
        this.parkingLotService = parkingLotService;
        this.ticketRepository = ticketRepository;
        this.assignSpotStrategy = assignSpotStrategy;
    }

    @Override
    public Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) throws Exception {
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
        VehicleType type = VehicleType.getVehicleTypeFromString(vehicleType); // Converting String to Enum
        Vehicle vehicle = vehicleService.createIfNotExists(vehicleNumber, type);
        ParkingLot parkingLot = parkingLotService.getParkingLotByGateId(gateId);
        if(parkingLot == null) throw new Exception("Invalid Gate Id");
        Spot assignedSpot = assignSpotStrategy.assignSpot(type, parkingLot);
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());
        ticket.setEntryGate(entryGate);
        ticket.setVehicle(vehicle);
        ticket.setAssignedSpot(assignedSpot);
        return ticketRepository.insertTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return ticketRepository.getTicketById(ticketId);
    }

}

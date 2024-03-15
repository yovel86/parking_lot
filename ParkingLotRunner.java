package machine_coding.parking_lot;

import machine_coding.parking_lot.controllers.TicketController;
import machine_coding.parking_lot.repositories.GateRepository;
import machine_coding.parking_lot.repositories.ParkingLotRepository;
import machine_coding.parking_lot.repositories.TicketRepository;
import machine_coding.parking_lot.repositories.VehicleRepository;
import machine_coding.parking_lot.services.*;
import machine_coding.parking_lot.strategies.spot_assignment.AssignSpotStrategy;
import machine_coding.parking_lot.strategies.spot_assignment.NearestFirstSpotAssignmentStrategy;

public class ParkingLotRunner {

    public static void main(String[] args) {

        GateRepository gateRepository = new GateRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();

        AssignSpotStrategy assignSpotStrategy = new NearestFirstSpotAssignmentStrategy();

        GateService gateService = new GateServiceImpl(gateRepository);
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(parkingLotRepository);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
        TicketService ticketService = new TicketServiceImpl(gateService, vehicleService, parkingLotService, ticketRepository, assignSpotStrategy);

        TicketController ticketController = new TicketController(ticketService);

    }

}

package machine_coding.parking_lot;

import machine_coding.parking_lot.controllers.InvoiceController;
import machine_coding.parking_lot.dtos.GenerateInvoiceRequestDTO;
import machine_coding.parking_lot.dtos.GenerateInvoiceResponseDTO;
import machine_coding.parking_lot.dtos.GenerateTicketRequestDTO;
import machine_coding.parking_lot.dtos.GenerateTicketResponseDTO;
import machine_coding.parking_lot.factories.CalculateFeesStrategyFactory;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.*;
import machine_coding.parking_lot.services.*;
import machine_coding.parking_lot.controllers.TicketController;
import machine_coding.parking_lot.strategies.spot_assignment.AssignSpotStrategy;
import machine_coding.parking_lot.strategies.spot_assignment.NearestFirstSpotAssignmentStrategy;

import java.util.*;

public class ParkingLotRunner {

    public static void main(String[] args) {

        // Dummy data
        Gate gate1 = new Gate();
        gate1.setGateType(GateType.ENTRY);
        gate1.setName("Gate 1");
        gate1.setOperator(new Operator());
        gate1.setId(1);
        Gate gate2 = new Gate();
        gate2.setGateType(GateType.EXIT);
        gate2.setName("Gate 2");
        gate2.setOperator(new Operator());
        gate2.setId(2);

        Map<Integer, Gate> gateData = new HashMap<>() {{
            put(1, gate1);
            put(2, gate2);
        }};

        List<Spot> spots = Arrays.asList(
                new Spot("A1", SpotStatus.UNOCCUPIED, VehicleType.CAR),
                new Spot("A2", SpotStatus.UNOCCUPIED, VehicleType.BIKE)
        );

        List<Section> sections = new ArrayList<>();
        Section section1 = new Section();
        section1.setId(1);
        section1.setName("Section A");
        section1.setSpots(spots);
        sections.add(section1);

        List<Floor> floors = new ArrayList<>();
        Floor floor1 = new Floor();
        floor1.setId(1);
        floor1.setFloorNum(1);
        floor1.setFloorStatus(FloorStatus.OPERATIONAL);
        floor1.setSections(sections);
        floors.add(floor1);

        List<Gate> gates = Arrays.asList(gate1, gate2);

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1);
        parkingLot.setFloors(floors);
        parkingLot.setGates(gates);

        Map<Integer, ParkingLot> parkingLotData = new HashMap<>() {{
            put(1, parkingLot);
        }};

        Slab slab1 = new Slab(1, VehicleType.CAR, 0, 2, 10);
        Slab slab2 = new Slab(2, VehicleType.CAR, 2, 4, 20);
        Slab slab3 = new Slab(3, VehicleType.CAR, 4, 8, 25);
        Slab slab4 = new Slab(4, VehicleType.CAR, 8, -1, 40);
        Map<Integer, Slab> slabData = new HashMap<>(){{
            put(1, slab1);
            put(2, slab2);
            put(3, slab3);
            put(4, slab4);
        }};
        SlabRepository slabRepository = new SlabRepository(slabData);
        SlabService slabService = new SlabServiceImpl(slabRepository);

        InvoiceRepository invoiceRepository = new InvoiceRepository();

        CalculateFeesStrategyFactory calculateFeesStrategyFactory = new CalculateFeesStrategyFactory(slabService);

        // Initializing Services & Repositories
        GateRepository gateRepository = new GateRepository(gateData);
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository(parkingLotData);
        VehicleRepository vehicleRepository = new VehicleRepository();
        TicketRepository ticketRepository = new TicketRepository();

        AssignSpotStrategy assignSpotStrategy = new NearestFirstSpotAssignmentStrategy();

        GateService gateService = new GateServiceImpl(gateRepository);
        ParkingLotService parkingLotService = new ParkingLotServiceImpl(parkingLotRepository);
        VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
        TicketService ticketService = new TicketServiceImpl(gateService, vehicleService, parkingLotService, ticketRepository, assignSpotStrategy);
        InvoiceService invoiceService = new InvoiceServiceImpl(ticketService, gateService, calculateFeesStrategyFactory, invoiceRepository);

        TicketController ticketController = new TicketController(ticketService);
        InvoiceController invoiceController = new InvoiceController(invoiceService);

        // Mimicking sending data from client in the format of DTO
        GenerateTicketRequestDTO generateTicketRequestDTO = new GenerateTicketRequestDTO(1, "TN 01 6532", VehicleType.CAR.toString());

        GenerateTicketResponseDTO ticketResponseDTO = ticketController.generateTicket(generateTicketRequestDTO);
        System.out.println(ticketResponseDTO);
        int ticketId = ticketResponseDTO.getTicket().getId();

        generateTicketRequestDTO.setVehicleNumber("KA 01 5432");
        ticketResponseDTO = ticketController.generateTicket(generateTicketRequestDTO);
        System.out.println(ticketResponseDTO);

        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Generate Invoice
        GenerateInvoiceRequestDTO invoiceRequestDTO = new GenerateInvoiceRequestDTO();
        invoiceRequestDTO.setTicketId(ticketId);
        invoiceRequestDTO.setGateId(2);

        GenerateInvoiceResponseDTO invoiceResponseDTO = invoiceController.generateInvoice(invoiceRequestDTO);
        System.out.println(invoiceResponseDTO);

    }

}

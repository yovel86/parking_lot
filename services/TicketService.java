package machine_coding.parking_lot.services;

import machine_coding.parking_lot.models.Ticket;
import machine_coding.parking_lot.models.VehicleType;

public interface TicketService {

    Ticket generateTicket(int gateId, String vehicleNumber, String vehicleType) throws Exception;

    Ticket getTicketById(int ticketId);

}

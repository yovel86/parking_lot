package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {

    private static int ID = 1;
    private Map<Integer, Ticket> ticketDB;

    public TicketRepository() {
        this.ticketDB = new HashMap<>();
    }

    public TicketRepository(Map<Integer, Ticket> data) {
        this.ticketDB = data;
    }

    public Ticket insertTicket(Ticket ticket) {
        ticket.setId(ID);
        ticketDB.put(ID++, ticket);
        return ticket;
    }

    public Ticket getTicketById(int ticketId) {
        return ticketDB.get(ticketId);
    }

}

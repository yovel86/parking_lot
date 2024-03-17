package machine_coding.parking_lot.services;

import machine_coding.parking_lot.exceptions.InvalidGateException;
import machine_coding.parking_lot.exceptions.InvalidTicketException;
import machine_coding.parking_lot.models.Gate;
import machine_coding.parking_lot.models.GateType;
import machine_coding.parking_lot.models.Invoice;
import machine_coding.parking_lot.models.Ticket;

public class InvoiceServiceImpl implements InvoiceService {

    private TicketService ticketService;
    private GateService gateService;

    public InvoiceServiceImpl(TicketService ticketService, GateService gateService) {
        this.ticketService = ticketService;
        this.gateService = gateService;
    }

    @Override
    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException {
        /*
            1. Check if Ticket obj exists in DB or not
            2. Check if Gate obj exists in DB not, or gate is an Entry gate
            3. Calculate charges via an appropriate strategy
            4. Create the Invoice, store it in DB & return
         */
        Ticket ticket = ticketService.getTicketById(ticketId);
        if(ticket == null) throw new InvalidTicketException("Ticket is not Valid, not present in DB");
        Gate exitGate = gateService.getGateById(gateId);
        if(exitGate == null) throw new InvalidGateException("Not a valid gate, not present in DB");
        if(exitGate.getGateType().equals(GateType.ENTRY)) throw new InvalidGateException("Invoice cannot be created at Entry gate");

        return null;
    }

}

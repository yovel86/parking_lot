package machine_coding.parking_lot.services;

import machine_coding.parking_lot.exceptions.InvalidGateException;
import machine_coding.parking_lot.exceptions.InvalidTicketException;
import machine_coding.parking_lot.factories.CalculateFeesStrategyFactory;
import machine_coding.parking_lot.models.*;
import machine_coding.parking_lot.repositories.InvoiceRepository;
import machine_coding.parking_lot.strategies.pricing_strategy.CalculateFeesStrategy;

import java.util.Arrays;
import java.util.Date;

public class InvoiceServiceImpl implements InvoiceService {

    private TicketService ticketService;
    private GateService gateService;
    private CalculateFeesStrategyFactory calculateFeesStrategyFactory;
    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(TicketService ticketService, GateService gateService, CalculateFeesStrategyFactory factory, InvoiceRepository invoiceRepository) {
        this.ticketService = ticketService;
        this.gateService = gateService;
        this.calculateFeesStrategyFactory = factory;
        this.invoiceRepository = invoiceRepository;
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
        if(ticket == null) throw new InvalidTicketException("Not a valid Ticket, not present in DB");

        Gate exitGate = gateService.getGateById(gateId);
        if(exitGate == null) throw new InvalidGateException("Not a valid Gate, not present in DB");
        if(exitGate.getGateType().equals(GateType.ENTRY)) throw new InvalidGateException("Invoice cannot be created at Entry gate");

        Date entryDate = ticket.getEntryTime();
        Date exitDate = new Date();
        CalculateFeesStrategy calculateFeesStrategy = calculateFeesStrategyFactory.getCalculateFeesStrategy(exitDate);
        double totalPrice = calculateFeesStrategy.calculateFees(entryDate, exitDate, ticket.getVehicle().getVehicleType());

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setName("Parking fees");
        invoiceDetail.setPrice(totalPrice);

        Invoice invoice = new Invoice();
        invoice.setInvoiceDetails(Arrays.asList(invoiceDetail));
        invoice.setTicket(ticket);
        invoice.setExitTime(exitDate);

        return invoiceRepository.insertInvoice(invoice);
    }

}

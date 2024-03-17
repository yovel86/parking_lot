package machine_coding.parking_lot.models;

import java.util.Date;
import java.util.List;

public class Invoice extends BaseModel {

    private Ticket ticket;
    private Date exitTime;
    private double totalAmount;
    private List<InvoiceDetail> invoiceDetails;

}

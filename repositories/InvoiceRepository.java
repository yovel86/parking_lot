package machine_coding.parking_lot.repositories;

import machine_coding.parking_lot.models.Invoice;

import java.util.HashMap;
import java.util.Map;

public class InvoiceRepository {

    private static int ID = 1;
    private Map<Integer, Invoice> invoiceDB;

    public InvoiceRepository() {
        this.invoiceDB = new HashMap<>();
    }

    public InvoiceRepository(Map<Integer, Invoice> invoiceDB) {
        this.invoiceDB = invoiceDB;
    }

    public Invoice insertInvoice(Invoice invoice) {
        invoice.setId(ID);
        invoiceDB.put(ID++, invoice);
        return invoice;
    }

}

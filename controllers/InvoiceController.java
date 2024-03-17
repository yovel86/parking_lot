package machine_coding.parking_lot.controllers;

import machine_coding.parking_lot.dtos.*;
import machine_coding.parking_lot.exceptions.InvalidRequestException;
import machine_coding.parking_lot.models.Invoice;
import machine_coding.parking_lot.services.InvoiceService;

public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public GenerateInvoiceResponseDTO generateInvoice(GenerateInvoiceRequestDTO requestDTO) {
        Response response = new Response();
        GenerateInvoiceResponseDTO responseDTO = new GenerateInvoiceResponseDTO();
        // Validate the Request DTO
        try {
            if(requestDTO.getTicketId() < 0) {
                throw new InvalidRequestException("Ticket Id cannot be negative");
            } else if(requestDTO.getGateId() < 0) {
                throw new InvalidRequestException("Gate Id cannot be negative");
            }
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMsg(e.getMessage());
            responseDTO.setResponse(response);
            return responseDTO;
        }
        // Call Invoice service & generate an invoice
        try {
            Invoice invoice = invoiceService.generateInvoice(requestDTO.getTicketId(), requestDTO.getGateId());
            responseDTO.setInvoice(invoice);
            response.setStatus(ResponseStatus.SUCCESS);
            responseDTO.setResponse(response);
            return responseDTO;
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMsg(e.getMessage());
            responseDTO.setResponse(response);
            return responseDTO;
        }
    }

}

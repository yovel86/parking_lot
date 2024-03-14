package machine_coding.parking_lot.controllers;

import machine_coding.parking_lot.dtos.GenerateTicketRequestDTO;
import machine_coding.parking_lot.dtos.GenerateTicketResponseDTO;
import machine_coding.parking_lot.dtos.Response;
import machine_coding.parking_lot.dtos.ResponseStatus;
import machine_coding.parking_lot.exceptions.InvalidRequestException;
import machine_coding.parking_lot.models.Ticket;
import machine_coding.parking_lot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDTO generateTicket(GenerateTicketRequestDTO requestDto) {
        Response response = new Response();
        GenerateTicketResponseDTO responseDTO = new GenerateTicketResponseDTO();
        // Validate the Request DTO
        try {
            if(requestDto.getGateId() < 0) {
                throw new InvalidRequestException("Invalid Gate ID");
            } else if(requestDto.getVehicleType() == null) {
                throw new InvalidRequestException("Vehicle Type is mandatory");
            } else if(requestDto.getVehicleNumber() == null) {
                throw new InvalidRequestException("Vehicle Number is mandatory");
            }
        } catch(InvalidRequestException e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMsg(e.getMessage());
        }
        // Extract and pass the data from requestDTO to Service layer to generate ticket
        // and receive the ticket and send it back to the client in the format of DTO
        try {
            Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getVehicleNumber(), requestDto.getVehicleType());
            responseDTO.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.FAILED);
            response.setErrorMsg(e.getMessage());
        }
        responseDTO.setResponse(response);
        return responseDTO;
    }

}

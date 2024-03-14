package machine_coding.parking_lot.dtos;

import machine_coding.parking_lot.models.Ticket;

public class GenerateTicketResponseDTO {

    private Ticket ticket;
    private Response response;

    public Response getResponse() { return response; }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}

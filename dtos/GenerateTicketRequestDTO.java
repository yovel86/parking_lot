package machine_coding.parking_lot.dtos;

import machine_coding.parking_lot.models.VehicleType;

public class GenerateTicketRequestDTO {

    private int gateId;
    private String vehicleNumber;
    private String vehicleType;

    public GenerateTicketRequestDTO(int gateId, String vehicleNumber, String vehicleType) {
        this.gateId = gateId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

}

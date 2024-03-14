package machine_coding.parking_lot.models;

import java.util.List;

public class ParkingLot extends BaseModel {

    private List<Gate> gates;
    private int id;
    private List<Floor> floors;

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

}

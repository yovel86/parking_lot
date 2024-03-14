package machine_coding.parking_lot.models;

import java.util.Date;

public class Ticket extends BaseModel {

    private Date entryTime;
    private Gate entryGate;
    private Vehicle vehicle;
    private Spot assignedSpot;

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Gate getEntryGate() {
        return entryGate;
    }

    public void setEntryGate(Gate entryGate) {
        this.entryGate = entryGate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Spot getAssignedSpot() {
        return assignedSpot;
    }

    public void setAssignedSpot(Spot assignedSpot) {
        this.assignedSpot = assignedSpot;
    }

}

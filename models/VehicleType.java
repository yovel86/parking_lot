package machine_coding.parking_lot.models;

public enum VehicleType {
    CAR,
    BIKE,
    TRUCK,
    EV_CAR;

    public static VehicleType getVehicleTypeFromString(String type) {
        for(VehicleType vehicleType: VehicleType.values()) {
            if(type.equalsIgnoreCase(vehicleType.toString())) {
                return vehicleType;
            }
        }
        throw new IllegalArgumentException("Unsupported Vehicle Type");
    }

}

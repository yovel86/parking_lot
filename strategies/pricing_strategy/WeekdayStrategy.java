package machine_coding.parking_lot.strategies.pricing_strategy;

import machine_coding.parking_lot.models.VehicleType;
import machine_coding.parking_lot.utils.DateTimeUtils;

import java.util.Date;

public class WeekdayStrategy implements CalculateFeesStrategy {

    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        double totalPrice = 0;
        int hours = DateTimeUtils.getDiffInHours(entryTime, exitTime);
        if(vehicleType.equals(VehicleType.BIKE)) totalPrice = hours * 10;
        else if(vehicleType.equals(VehicleType.CAR)) totalPrice = hours * 15;
        return totalPrice;
    }

}

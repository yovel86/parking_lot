package machine_coding.parking_lot.strategies.pricing_strategy;

import machine_coding.parking_lot.models.Slab;
import machine_coding.parking_lot.models.VehicleType;
import machine_coding.parking_lot.services.SlabService;
import machine_coding.parking_lot.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekendStrategy implements CalculateFeesStrategy {

    private SlabService slabService;

    public WeekendStrategy(SlabService slabService) {
        this.slabService = slabService;
    }

    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = slabService.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtils.getDiffInHours(entryTime, exitTime);
        double totalPrice = 0;
        for(Slab slab: slabs) {
            if(hours >= slab.getStartHour() && slab.getEndHour() != -1) {
                if(hours >= slab.getEndHour()) {
                    totalPrice += (slab.getEndHour() - slab.getStartHour()) * slab.getPricePerHour();
                } else {
                    totalPrice += (hours - slab.getStartHour()) * slab.getPricePerHour();
                }
            } else if(slab.getEndHour() == -1){
                totalPrice += (hours - slab.getStartHour()) * slab.getPricePerHour();
            } else break;
        }
        return totalPrice;
    }

}

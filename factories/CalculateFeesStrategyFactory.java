package machine_coding.parking_lot.factories;

import machine_coding.parking_lot.services.SlabService;
import machine_coding.parking_lot.strategies.pricing_strategy.CalculateFeesStrategy;
import machine_coding.parking_lot.strategies.pricing_strategy.WeekdayStrategy;
import machine_coding.parking_lot.strategies.pricing_strategy.WeekendStrategy;

import java.util.Calendar;
import java.util.Date;

public class CalculateFeesStrategyFactory {

    private SlabService slabService;

    public CalculateFeesStrategyFactory(SlabService slabService) {
        this.slabService = slabService;
    }

    public CalculateFeesStrategy getCalculateFeesStrategy(Date exitDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(exitDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = day == Calendar.SATURDAY || day == Calendar.SUNDAY;
        CalculateFeesStrategy calculateFeesStrategy;
        if(isWeekend) calculateFeesStrategy = new WeekendStrategy(slabService);
        else calculateFeesStrategy = new WeekdayStrategy();
        return calculateFeesStrategy;
    }

}

package machine_coding.parking_lot.utils;

import java.util.Date;

public class DateTimeUtils {

    public static int getDiffInHours(Date start, Date end) {
        long diffInMilliSecs = end.getTime() - start.getTime();
        long diffInSecs = diffInMilliSecs / 1000;                     // Converting milliseconds to seconds
        return (int) Math.ceil((double) diffInSecs / 3600);      // Converting seconds to hours
    }

}

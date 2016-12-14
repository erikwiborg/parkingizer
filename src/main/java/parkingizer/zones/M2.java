package parkingizer.zones;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 * Created by erik on 09/12/2016.
 *
 -          100 kroner per påbegynte time på hverdager.
 -          I helgen er prisen det dobbelte, altså 200 kroner per påbegynte time.
 */
public class M2 implements Zone {

    private static final int WEEKDAY_PRICE_PER_HOUR = 100;
    private static final int WEEKEND_PRICE_PER_HOUR = 200;
    @Override
    public int calculate(DateTime start, DateTime end) {
        int result = 0;
        while(start.isBefore(end)) {
            result += start.dayOfWeek().get() > DateTimeConstants.FRIDAY ? WEEKEND_PRICE_PER_HOUR : WEEKDAY_PRICE_PER_HOUR;
            start = start.plusHours(1);
        }
        return result;
    }
}

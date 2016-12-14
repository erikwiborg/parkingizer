package parkingizer.zones;

import org.joda.time.DateTime;

/**
 * Created by erik on 09/12/2016.
 * -          60 kroner timen, beregnet for hvert påbegynte minutt.
 */
public class M1 implements Zone {

    private static final int PRICE_PER_HOUR = 60;
    @Override
    public int calculate(DateTime start, DateTime end) {
        int result = 0;
        while(start.isBefore(end)) {
            result += PRICE_PER_HOUR;
            start = start.plusHours(1);
        }
        return result;
    }
}

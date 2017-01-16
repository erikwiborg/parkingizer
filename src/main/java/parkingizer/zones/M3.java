package parkingizer.zones;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;


/**
 * Created by erik on 13/12/2016.
 * -        Mandag til lørdag mellom 08:00 og 16:00 er første time gratis, deretter koster det 2 kroner per påbegynte minutt.
 -          Mandag til lørdag utenom disse tidspunktene koster det 3 kroner minuttet.
 -          Søndager er parkering gratis.

 */
public class M3 implements Zone {
    static final int PRICE_PER_MINUTE_BUSINESS_HOURS = 2;
    static final int PRICE_PER_MINUTE = 3;
    private static final int MINUTES_FREE_BUSINESS_HOURS = 60;
    @Override
    public int calculate(DateTime start, DateTime end) {
        int result = 0;
        int minuteCounter = 0;
        while(!start.isAfter(end)) {
            //If sunday, free.
            if(start.dayOfWeek().get() != DateTimeConstants.SUNDAY) {
                if (start.hourOfDay().get() >= 8 && !start.isAfter(start.withTime(16, 0, 0 ,0))) {
                    result += minuteCounter <= MINUTES_FREE_BUSINESS_HOURS ? 0 : PRICE_PER_MINUTE_BUSINESS_HOURS;
                    minuteCounter++;
                } else {
                    result += PRICE_PER_MINUTE;
                }
            }
            start = start.plusMinutes(1);

        }
        return result;
    }
}
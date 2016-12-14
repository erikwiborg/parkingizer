package parkingizer.zones;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Minutes;


/**
 * Created by erik on 13/12/2016.
 * -        Mandag til lørdag mellom 08:00 og 16:00 er første time gratis, deretter koster det 2 kroner per påbegynte minutt.
 -          Mandag til lørdag utenom disse tidspunktene koster det 3 kroner minuttet.
 -          Søndager er parkering gratis.

 */
public class M3 implements Zone {
    private static final int PRICE_PER_MINUTE_BUSINESS_HOURS = 2;
    private static final int PRICE_PER_MINUTE = 3;
    @Override
    public int calculate(DateTime start, DateTime end) {
        int result = 0;
        while(start.isBefore(end)) {
            //If sunday, free.
            if(start.dayOfWeek().get() != DateTimeConstants.SUNDAY) {
                if (start.hourOfDay().get() >= 8 && start.hourOfDay().get() < 16) {
                    result += Minutes.minutesBetween(start, end).getMinutes() <= 60 ? 0 : PRICE_PER_MINUTE_BUSINESS_HOURS;
                } else {
                    result += PRICE_PER_MINUTE;
                }
            }
            start = start.plusMinutes(1);
        }
        return result;
    }
}

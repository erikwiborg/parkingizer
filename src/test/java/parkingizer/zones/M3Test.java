package parkingizer.zones;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static parkingizer.zones.M3.PRICE_PER_MINUTE;
import static parkingizer.zones.M3.PRICE_PER_MINUTE_BUSINESS_HOURS;

/**
 * Created by erik on 13/12/2016.
 */
public class M3Test {

    @Test
    public void shouldBeFreeOnSundays() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.SUNDAY).hourOfDay().setCopy(5);
        DateTime then = now.plusHours(2);
        assertEquals(0, new M3().calculate(now, then));
    }

    @Test
    public void shouldBeFreeForLessThanOneHour() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.THURSDAY).hourOfDay().setCopy(9);
        DateTime then = now.plusHours(1);
        assertEquals(0, new M3().calculate(now, then));
    }


    @Test
    public void shouldBeFreeForTheFirstHourAndThenCharge2PerMinute() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.THURSDAY).withTime(9, 0, 0, 0);
        DateTime then = now.plusHours(2).plusMinutes(5);
        assertEquals(PRICE_PER_MINUTE_BUSINESS_HOURS * 65, new M3().calculate(now, then));
    }

    @Test
    public void shouldPayForMinutesBeforeEightAndThenGetOneHourFree() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.THURSDAY).withTime(7, 30, 0, 0);
        DateTime then = now.plusHours(2).plusMinutes(5);

        assertEquals((PRICE_PER_MINUTE * 30) + (PRICE_PER_MINUTE_BUSINESS_HOURS * 35), new M3().calculate(now, then));
    }

    @Test
    public void shouldPayForMinutesAfterFour() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.THURSDAY).withTime(15, 30, 0, 0);
        DateTime then = now.plusHours(2).plusMinutes(5);

        assertEquals((95 * PRICE_PER_MINUTE), new M3().calculate(now, then));
    }


    @Test
    public void shouldPayForBeforeAndAfterBusinessHours() throws Exception {
        DateTime now = new DateTime().dayOfWeek().setCopy(DateTimeConstants.THURSDAY).withTime(7, 30, 0, 0);
        DateTime then = now.plusHours(12).plusMinutes(5);

        assertEquals((30 * PRICE_PER_MINUTE) + (7 * 60 * PRICE_PER_MINUTE_BUSINESS_HOURS) + (3 * 60 * PRICE_PER_MINUTE) + (35 * PRICE_PER_MINUTE), new M3().calculate(now, then));
    }
}
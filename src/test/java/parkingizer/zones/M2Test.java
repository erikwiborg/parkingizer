package parkingizer.zones;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by erik on 09/12/2016.
 */
public class M2Test {
    @Test
    public void shouldReturnPriceForOneHour() throws Exception {
        DateTime now = new DateTime();
        DateTime then = now.plusMinutes(58);
        assertEquals(100, new M2().calculate(now, then));
    }
    @Test
    public void shouldReturnPriceForTwoHours() throws Exception {
        DateTime now = new DateTime();
        DateTime then = now.plusMinutes(62);
        assertEquals(200, new M2().calculate(now, then));
    }
    @Test
    public void shouldReturnPriceForOneHourOnASaturday() throws Exception {
        DateTime now = new DateTime();
        now = now.dayOfWeek().setCopy(DateTimeConstants.SATURDAY);
        DateTime then = now.plusMinutes(25);
        assertEquals(200, new M2().calculate(now, then));
    }

    @Test
    public void shouldReturnPriceForTwoHoursOnASaturday() throws Exception {
        DateTime now = new DateTime();
        now = now.dayOfWeek().setCopy(DateTimeConstants.SATURDAY);
        DateTime then = now.plusMinutes(62);
        assertEquals(400, new M2().calculate(now, then));
    }

    @Test
    public void shouldReturnPriceForTwoHoursFromFridayToSaturday() throws Exception {
        DateTime now = new DateTime();
        now = now.dayOfWeek().setCopy(DateTimeConstants.FRIDAY).hourOfDay().setCopy(23);
        DateTime then = now.plusMinutes(62);
        assertEquals(300, new M2().calculate(now, then));
    }

}
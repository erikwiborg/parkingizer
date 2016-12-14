package parkingizer.zones;

import org.joda.time.DateTime;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by erik on 09/12/2016.
 */
public class M1Test {
    @Test
    public void shouldReturnPriceForOneHour() throws Exception {
        DateTime now = new DateTime();
        DateTime then = now.plusMinutes(58);
        assertEquals(60, new M1().calculate(now, then));
    }
    @Test
    public void shouldReturnPriceForTwoHours() throws Exception {
        DateTime now = new DateTime();
        DateTime then = now.plusMinutes(62);
        assertEquals(120, new M1().calculate(now, then));
    }

}
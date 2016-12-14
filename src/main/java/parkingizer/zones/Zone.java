package parkingizer.zones;

import org.joda.time.DateTime;

/**
 * Created by erik on 09/12/2016.
 */
public interface Zone {

    int calculate(DateTime start, DateTime end);
}

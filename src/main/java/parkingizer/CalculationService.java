package parkingizer;

import org.joda.time.DateTime;
import parkingizer.zones.M1;
import parkingizer.zones.M2;
import parkingizer.zones.M3;
import parkingizer.zones.Zone;

class CalculationService {

    //might be handy to return the parameters, so build a response object here
    static ResponseContent calculate(String zoneName, DateTime start, DateTime end) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return new ResponseContent(zoneName, start.toString(), end.toString(), zoneLookup(zoneName).calculate(start, end));
    }

    //might use reflection here, but... yeah.
    private static Zone zoneLookup (String zoneName) throws ClassNotFoundException {
        switch(zoneName) {
            case "M1": return new M1();
            case "M2": return new M2();
            case "M3": return new M3();
            default: throw new ClassNotFoundException("Could not find zone " + zoneName);
        }
    }
}

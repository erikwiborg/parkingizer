package parkingizer;

import com.google.gson.Gson;
import org.joda.time.DateTime;

import static spark.Spark.get;
import static spark.Spark.port;


public class Parkingizer {

    public static void main(String[] args) {
        port(5000);
        get("/takst/zone/:zone/start/:start/end/:end", (request, response) -> {
                    try {
                        return CalculationService.calculate(
                                request.params("zone"),
                                new DateTime(new Long(request.params("start")) * 1000),
                                new DateTime(new Long(request.params("end")) * 1000)
                        );
                    } catch (Exception e) {
                        //yeah, should return something more friendly.
                        return "Something went wrong: " + e.toString();
                    }
                },
                model -> new Gson().toJson(model)
        );
    }
}
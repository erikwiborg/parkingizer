package parkingizer;

/**
 * Created by erik on 15/12/2016.
 */
class ResponseContent {

    private String zone;
    private String startTime;
    private String endTime;
    private Number amount;


    public ResponseContent(String zone, String startTime, String endTime, Number amount) {
        this.zone = zone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    public String getZone() {
        return zone;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public Number getAmount() {
        return amount;
    }
}

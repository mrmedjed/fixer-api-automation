package api.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class TimeseriesResponse {

    private boolean success;
    private long timestamp;
    private String base;
    private String start_date;
    private String end_date;
    private boolean timeseries;
    private Map<String, Map<String, Double>> rates;
    private Error error;
}

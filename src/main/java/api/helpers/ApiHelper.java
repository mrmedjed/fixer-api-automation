package api.helpers;

import api.utils.ConfigUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ApiHelper {

    static {
        RestAssured.baseURI = ConfigUtil.getBaseUrl();
    }

    public static Response getTimeseries(String apiKeyType, String startDate, String endDate, String base, String symbols) {
        if (apiKeyType == null || apiKeyType.isEmpty()) {
            throw new IllegalArgumentException("Missing API key!");
        }

        var request = given().header("apikey", ConfigUtil.getApiKey(apiKeyType));

        if (startDate != null && !startDate.isEmpty()) {
            request.queryParam("start_date", startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            request.queryParam("end_date", endDate);
        }
        if (base != null && !base.isEmpty()) {
            request.queryParam("base", base);
        }
        if (symbols != null && !symbols.isEmpty()) {
            request.queryParam("symbols", symbols);
        }

        return request.when().get("/timeseries")
                .then().log().all()
                .extract().response();
    }
}

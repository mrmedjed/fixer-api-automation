package api.stepDefinitions;

import api.helpers.ApiHelper;
import api.models.TimeseriesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TimeseriesSteps {

    private Response response;
    private TimeseriesResponse timeseriesResponse;
    private String apiKeyType;

    @Given("I use {string} API key")
    public void i_use_api_key(String apiKeyType) {
        this.apiKeyType = apiKeyType;
    }

    @When("I request timeseries data with start date {string}, end date {string}, base {string}, and symbols {string}")
    public void i_request_timeseries_data_with_access_key_start_date_end_date_base_and_symbols(String startDate, String endDate, String base, String symbols) {
        response = ApiHelper.getTimeseries(apiKeyType, startDate, endDate, base, symbols);
        if (response.getStatusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                timeseriesResponse = objectMapper.readValue(response.getBody().asString(), TimeseriesResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Failed to parse timeseries response");
            }
        }
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("the response success should be {string}")
    public void the_response_success_should_be(String success) {
        assertEquals(success.equals("true"), timeseriesResponse.isSuccess());
    }
}

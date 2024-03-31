package org.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.utils.RestClient;

import java.io.FileInputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    RestClient restClient = new RestClient();

    Response response;
    String baseUrl;
    String endPoint;
    String incorretBaseUrl;

    public void init(){
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
            baseUrl = properties.getProperty("baseUrl");
            endPoint = properties.getProperty("endPoint");
            incorretBaseUrl = properties.getProperty("incorretBaseUrl");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Given("user has the base uri configured")
    public void setBaseURI() {
        init();
        restClient.setBaseURI(baseUrl);
    }

    @When("user send a GET request having {string} header")
    public void user_send_a_get_request_having_header(String authorizationToken) {
        response = restClient.sendGETRequest(endPoint,authorizationToken);
    }

    @Given("user has the base url configured")
    public void setBaseURL() {
        init();
        restClient.setBaseURI(baseUrl);
    }

    @Given("user has the incorrect base url configured")
    public void setIncorrectBaseUrl(){
        init();
        restClient.setBaseURI(incorretBaseUrl);
    }

    @When("user send a {string} request having {string} header")
    public void sendRequestWithAuthorizationToken(String methodType, String authorizationToken) {
        switch (methodType) {
            case "GET":
                response = restClient.sendGETRequest(endPoint,authorizationToken);
                break;
            case "PUT":
                response = restClient.sendPUTRequest(endPoint,authorizationToken);
                break;
            case "POST":
                response = restClient.sendPOSTRequest(endPoint,authorizationToken);
                break;
            case "PATCH":
                response = restClient.sendPATCHRequest(endPoint,authorizationToken);
                break;
            case "DELETE":
                response = restClient.sendDELETERequest(endPoint,authorizationToken);
                break;
        }
    }


    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response body should contain {string}")
    public void verifyResponseBodyContains(String expectedContent) {
        restClient.verifyResponseBodyContains(response, expectedContent);
    }
}

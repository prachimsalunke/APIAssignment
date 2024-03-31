package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestClient {
    private String baseURI;

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public Response sendGETRequest(String endpoint, String authorizationToken) {
        if (authorizationToken != null) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .header("authorizationToken", authorizationToken)
                    .get(endpoint);
        } else {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .get(endpoint);
        }
    }

    public Response sendPUTRequest(String endpoint,String authorizationToken) {
        if (authorizationToken != null) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .header("authorizationToken", authorizationToken)
                    .put();
        } else {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .put();
        }
    }

    public Response sendPOSTRequest(String endpoint,String authorizationToken) {
        if (authorizationToken != null) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .header("authorizationToken", authorizationToken)
                    .post();
        } else {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .post();
        }
    }

    public Response sendPATCHRequest(String endpoint,String authorizationToken) {
        if (authorizationToken != null) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .header("authorizationToken", authorizationToken)
                    .patch();
        } else {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .patch();
        }
    }

    public Response sendDELETERequest(String endpoint,String authorizationToken) {
        if (authorizationToken != null) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .header("authorizationToken", authorizationToken)
                    .delete();
        } else {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .delete();
        }
    }

    public void verifyResponseBodyContains(Response response, String expectedContent) {
        response.then()
                .assertThat()
                .body(org.hamcrest.Matchers.containsString(expectedContent));
    }

    public void verifyStatusCode(Response response, int expectedStatusCode) {
        response.then()
                .assertThat()
                .statusCode(expectedStatusCode);
    }
}
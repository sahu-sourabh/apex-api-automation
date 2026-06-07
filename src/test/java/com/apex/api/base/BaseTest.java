package com.apex.api.base;

import com.apex.api.utils.ConfigReader;
import com.apex.api.utils.TestListener;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {
    
    private static RequestSpecification baseRequestSpec;

    @BeforeSuite
    public void globalSetup() {
        String targetBaseUrl = ConfigReader.getProperty("base.url");

        // We build the spec but do not pollute the global RestAssured state directly
        baseRequestSpec = new RequestSpecBuilder()
                .setBaseUri(targetBaseUrl)
                .setBasePath("/api") // Clean separation of Base URI and API Base Path
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * For public/anonymous endpoints (e.g., User Registration or Login)
     */
    public RequestSpecification publicSpec() {
        return given().spec(baseRequestSpec);
    }

    /**
     * For authenticated endpoints requiring an Authorization token
     */
    public RequestSpecification authSpec(String token) {
        return given()
                .spec(baseRequestSpec)
                .header("Authorization", "Token " + token);
    }

    // =========================================================================
    // Core Execution Engines
    // =========================================================================

    public Response executePost(RequestSpecification spec, Object payload, String endpoint, int expectedStatusCode) {
        return given()
                .spec(spec)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public Response executeGet(RequestSpecification spec, String endpoint, int expectedStatusCode) {
        return given()
                .spec(spec)
                .when()
                .get(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public Response executePut(RequestSpecification spec, Object payload, String endpoint, int expectedStatusCode) {
        return given()
                .spec(spec)
                .body(payload)
                .when()
                .put(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public void executeDelete(RequestSpecification spec, String endpoint, int expectedStatusCode) {
        given()
                .spec(spec)
                .when()
                .delete(endpoint)
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode);
    }
}
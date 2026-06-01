package com.apex.api.tests;

import com.apex.api.base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HttpBinTests extends BaseTest {

    @Test(description = "Verify GET request using global framework configurations")
    public void verifyFrameworkGetCall() {
        // BaseTest handles Content-Type and BaseURI globally
        given()
                .queryParam("category", "automation")
                .when()
                .get("/get")// Appends directly to https://httpbin.org from config
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("args.category", equalTo("automation"));
    }
}

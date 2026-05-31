package com.apex.api;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class SmokeTest {

    @Test
    public void testPublicAPI() {
        given()
                .log().all()
                .when()
                .get("https://httpbin.org/get")
                .then()
                .log().all()
                .statusCode(200);
    }
}
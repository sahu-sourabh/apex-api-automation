package com.apex.api.base;

import com.apex.api.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void globalSetup() {
        // Pull target configuration values dynamically from utility
        String targetBaseUrl = ConfigReader.getProperty("base.url");

        // Define default request specifications applied globally to all API calls
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(targetBaseUrl)
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .build();
    }
}

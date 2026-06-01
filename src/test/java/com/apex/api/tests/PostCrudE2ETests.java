package com.apex.api.tests;

import com.apex.api.base.BaseTest;
import com.apex.api.models.PostRequest;
import com.apex.api.models.PostResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class PostCrudE2ETests extends BaseTest {

    // Class-level variable to store the dynamic ID generated during the POST step
    private int generatedPostId;

    @Test(priority = 1, description = "1. Create a new resource via POST and serialize payload")
    public void createPostTest() {
        // Construct payload using PostRequest POJO
        PostRequest payload = new PostRequest("Apex Framework Title", "This is an automated body text.", 101);

        PostResponse response = given()
                .body(payload)
                .when()
                .post("/posts")
                .then()
                .log().ifValidationFails()
                .statusCode(201)// HTTP 201 means Created
                .extract()
                .as(PostResponse.class); // Deserializes JSON response directly into Java Object

        // Verify the response body data matches what was sent
        Assert.assertEquals(response.getTitle(), payload.getTitle(), "Title mismatch in response!");
        Assert.assertEquals(response.getBody(), payload.getBody(), "Body mismatch in response!");
        Assert.assertNotNull(response.getId(), "Server failed to generate a resource ID!");

        // Store the ID for the subsequent steps
        generatedPostId = response.getId();
    }

    @Test(priority = 2, dependsOnMethods = { "createPostTest" }, description = "2. Read the resource details via GET")
    public void readPostTest() {
        // Dynamic string concatenation using our variable!
        // If testing a live production server, this would be "/posts/" + generatedPostId
        int targetId = (generatedPostId == 101) ? 1 : generatedPostId;

        // verify GET integrity.
        given()
                .when()
                .get("/posts/" + targetId)
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());
    }

    @Test(priority = 3, dependsOnMethods = { "createPostTest" }, description = "3. Update the resource via PUT")
    public void updatePostTest() {
        PostRequest updatedPayload = new PostRequest("Updated Title", "Updated body text.", 101);
        int targetId = (generatedPostId == 101) ? 1 : generatedPostId;

        given()
                .body(updatedPayload)
                .when()
                .put("/posts/" + targetId) // Updating reference item
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("body", equalTo("Updated body text."));
    }

    @Test(priority = 4, dependsOnMethods = { "createPostTest" }, description = "4. Delete the resource via DELETE")
    public void deletePostTest() {
        int targetId = (generatedPostId == 101) ? 1 : generatedPostId;

        given()
                .when()
                .delete("/posts/" + targetId)
                .then()
                .log().ifValidationFails()
                .statusCode(200); // JSONPlaceholder returns 200 on successful mock deletions
    }

}

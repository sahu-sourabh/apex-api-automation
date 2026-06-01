package com.apex.api.tests;

import com.apex.api.base.BaseTest;
import com.apex.api.models.PostRequest;
import com.apex.api.models.PostResponse;
import com.apex.api.utils.ConfigReader;
import com.apex.api.utils.TestData;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.testng.Assert;

public class PostCrudE2ETests extends BaseTest {

    // Class-level variable to store the dynamic ID generated during the POST step
    private int generatedPostId;
    // Read endpoint path dynamically from config
    private final String postsRoute = ConfigReader.getProperty("endpoint.posts");

    @Test(priority = 1, description = "1. Create a new resource via POST and serialize payload")
    public void createPostTest() {
        // Construct payload using PostRequest POJO, Data populated completely from TestData layer
        PostRequest payload = new PostRequest(TestData.POST_TITLE, TestData.POST_BODY, TestData.POST_USER_ID);

        PostResponse response = given()
                .body(payload)
                .when()
                .post(postsRoute) // Dynamic Route
                .then()
                .log().ifValidationFails()
                .statusCode(TestData.TWO_HUNDRED_ONE)// HTTP 201 means Created
                .extract()
                .as(PostResponse.class); // Deserializes JSON response directly into Java Object

        // Verify the response body data matches what was sent, Standardized dynamic assertions
        Assert.assertEquals(response.getTitle(), payload.getTitle(), TestData.ERR_TITLE_MISMATCH);
        Assert.assertEquals(response.getBody(), payload.getBody(), TestData.ERR_BODY_MISMATCH);
        Assert.assertNotNull(response.getId(), TestData.ERR_ID_NULL);

        // Store the ID for the subsequent steps
        generatedPostId = response.getId();
    }

    @Test(priority = 2, dependsOnMethods = { "createPostTest" }, description = "2. Read the resource details via GET")
    public void readPostTest() {
        // Dynamic string concatenation using variable!
        // If testing a live production server, this would be "/posts/" + generatedPostId
        int targetId = (generatedPostId == TestData.POST_USER_ID) ? TestData.ONE : generatedPostId;

        // verify GET integrity.
        given()
                .when()
                .get(postsRoute + "/" + targetId) // Dynamic routing concatenation
                .then()
                .log().ifValidationFails()
                .statusCode(TestData.TWO_HUNDRED)
                .body(TestData.ID, equalTo(TestData.ONE))
                .body(TestData.TITLE, notNullValue());
    }

    @Test(priority = 3, dependsOnMethods = { "createPostTest" }, description = "3. Update the resource via PUT")
    public void updatePostTest() {
        PostRequest updatedPayload = new PostRequest(TestData.UPDATE_TITLE, TestData.UPDATE_BODY, TestData.POST_USER_ID);
        int targetId = (generatedPostId == TestData.POST_USER_ID) ? TestData.ONE : generatedPostId;

        given()
                .body(updatedPayload)
                .when()
                .put(postsRoute + "/" + targetId) // Updating reference item
                .then()
                .log().ifValidationFails()
                .statusCode(TestData.TWO_HUNDRED)
                .body(TestData.TITLE, equalTo(TestData.UPDATE_TITLE))
                .body(TestData.BODY, equalTo(TestData.UPDATE_BODY));
    }

    @Test(priority = 4, dependsOnMethods = { "createPostTest" }, description = "4. Delete the resource via DELETE")
    public void deletePostTest() {
        int targetId = (generatedPostId == TestData.POST_USER_ID) ? TestData.ONE : generatedPostId;

        given()
                .when()
                .delete(postsRoute + "/" + targetId)
                .then()
                .log().ifValidationFails()
                .statusCode(TestData.TWO_HUNDRED); // JSONPlaceholder returns 200 on successful mock deletions
    }

}
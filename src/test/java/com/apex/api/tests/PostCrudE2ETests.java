package com.apex.api.tests;

import com.apex.api.base.BaseTest;
import com.apex.api.models.article.PostArticleRequest;
import com.apex.api.models.article.PostArticleResponse;
import com.apex.api.models.article.RequestArticle;
import com.apex.api.models.user.PostUserRequest;
import com.apex.api.models.user.PostUserResponse;
import com.apex.api.models.user.RequestUser;
import com.apex.api.utils.TestData;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.util.List;

public class PostCrudE2ETests extends BaseTest {
        LocalDateTime currenDateTime = LocalDateTime.now();
        private String token;
        private String slug;
        private String username = "java_user" + currenDateTime;
        private String email = "java_user" + currenDateTime + "@enterprise.com";
        private String password = "NoLombok123";

        @Test(priority = 1, description = "1. Create a new user via POST and extract token")
        public void generateTokenTest() {
                // Arrange
                PostUserRequest payload = new PostUserRequest()
                                .setuser(
                                                new RequestUser()
                                                                .setUsername(username)
                                                                .setEmail(email)
                                                                .setPassword(password));

                // Act
                PostUserResponse response = given()
                                .body(payload)
                                .when()
                                .post("/api/users")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(TestData.TWO_HUNDRED_ONE)// HTTP 201 means Created
                                .extract()
                                .as(PostUserResponse.class); // Deserializes JSON response directly into Java Object

                // Assert
                // Store the Token for the subsequent steps
                token = response.getUser().getToken();
                Assert.assertNotNull(token, "Token Null");
        }

        @Test(priority = 2, dependsOnMethods = {
                        "generateTokenTest" }, description = "2. Create a new article via POST")
        public void createArticleTest() {
                // Arrange
                PostArticleRequest payload = new PostArticleRequest()
                                .setArticle(
                                                new RequestArticle()
                                                                .setTitle("Rest Assured Article")
                                                                .setDescription("Testing via Rest Assured")
                                                                .setBody("This article was generated instantly via a backend POST API request.")
                                                                .setTagList(List.of("Java", "Rest Assured")));

                // Act
                PostArticleResponse response = given()
                                .header("Authorization", "Token " + token)
                                .body(payload)
                                .when()
                                .post("/api/articles")
                                .then()
                                .log().ifValidationFails()
                                .statusCode(TestData.TWO_HUNDRED_ONE)// HTTP 201 means Created
                                .extract()
                                .as(PostArticleResponse.class); // Deserializes JSON response directly into Java Object

                // Asset
                // Store the slug for the subsequent steps
                slug = response.getArticle().getSlug();
                Assert.assertNotNull(slug, "Post Slug Empty");
        }

        @Test(priority = 3, dependsOnMethods = {
                        "createArticleTest" }, description = "3. Read the article details via GET")
        public void readArticleTest() {

                // verify GET integrity.
                PostArticleResponse response = given()
                                .header("Authorization", "Token " + token)
                                .when()
                                .get("/api/articles/" + slug) // Dynamic routing concatenation
                                .then()
                                .log().ifValidationFails()
                                .statusCode(TestData.TWO_HUNDRED)
                                .extract()
                                .as(PostArticleResponse.class);

                // Assert
                Assert.assertEquals(response.getArticle().getSlug(), slug, "Get Slug Mismatch");
        }

        @Test(priority = 4, dependsOnMethods = { "createArticleTest" }, description = "4. Update the article via PUT")
        public void updateArticleTest() {
                // Arrange
                PostArticleRequest updatedPayload = new PostArticleRequest()
                                .setArticle(
                                                new RequestArticle()
                                                                .setTitle("Updated Article")
                                                                .setDescription("Updated Testing")
                                                                .setBody("This article was updated instantly via a backend PUT API request.")
                                                                .setTagList(List.of("Java", "Rest Assured",
                                                                                "Selenium")));

                // Act
                PostArticleResponse response = given()
                                .header("Authorization", "Token " + token)
                                .body(updatedPayload)
                                .when()
                                .put("/api/articles/" + slug) // Updating reference item
                                .then()
                                .log().ifValidationFails()
                                .statusCode(TestData.TWO_HUNDRED)
                                .extract()
                                .as(PostArticleResponse.class);

                slug = response.getArticle().getSlug();
                // Assert
                Assert.assertNotNull(slug, "Put Slug Empty");
        }

        @Test(priority = 5, dependsOnMethods = {
                        "createArticleTest" }, description = "5. Delete the article via DELETE")
        public void deleteArticleTest() {

                given()
                                .header("Authorization", "Token " + token)
                                .when()
                                .delete("/api/articles/" + slug)
                                .then()
                                .log().ifValidationFails()
                                .statusCode(204);
        }

}
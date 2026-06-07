package com.apex.api.tests;

import com.apex.api.base.BaseTest;
import com.apex.api.models.article.PostArticleRequest;
import com.apex.api.models.article.PostArticleResponse;
import com.apex.api.models.article.RequestArticle;
import com.apex.api.models.user.PostUserRequest;
import com.apex.api.models.user.PostUserResponse;
import com.apex.api.models.user.RequestUser;
import com.apex.api.utils.TestData;
import com.apex.api.utils.ConfigReader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.List;

public class ArticleCrudE2ETests extends BaseTest {
        private String token;
        private String slug;

        // 1. Data Generator Initialization
        private static final net.datafaker.Faker faker = new net.datafaker.Faker();

        // 2. Runtime Unique Dynamic Fields
        private final String username = "user_" + faker.internet().username();
        private final String email = faker.internet().emailAddress();

        // 3. Isolated Secure Credential Configuration Lookups
        private final String password = ConfigReader.getProperty("test.password");

        @Test(priority = 1, description = "Should successfully register a new user and extract auth token")
        public void registerNewUserAndVerifyToken() {
                // Arrange
                PostUserRequest payload = new PostUserRequest()
                                .setUser(new RequestUser()
                                                .setUsername(username)
                                                .setEmail(email)
                                                .setPassword(password));

                // Act
                PostUserResponse response = executePost(publicSpec(), payload, "/users", TestData.STATUS_CREATED)
                                .as(PostUserResponse.class);

                // Assert
                token = response.getUser().getToken();
                Assert.assertNotNull(token, "Verification Failed: Authentication token returned null!");
        }

        @Test(priority = 2, dependsOnMethods = { "registerNewUserAndVerifyToken" }, 
              description = "Should successfully create a new article and verify payload mapping matches")
        public void createNewArticleAndVerifyPayload() {
                // Arrange
                RequestArticle articleData = new RequestArticle()
                                .setTitle("Rest Assured Article " + System.currentTimeMillis())
                                .setDescription("Testing via Rest Assured Engine")
                                .setBody("Detailed core context text verification body.")
                                .setTagList(List.of("Java", "RestAssured"));

                PostArticleRequest payload = new PostArticleRequest().setArticle(articleData);

                // Act
                PostArticleResponse response = executePost(authSpec(token), payload, "/articles", TestData.STATUS_CREATED)
                                .as(PostArticleResponse.class);

                // Assert
                slug = response.getArticle().getSlug();
                Assert.assertNotNull(slug, TestData.ERR_SLUG_NULL);

                // Deep Object Verification
                Assert.assertEquals(response.getArticle().getTitle(), articleData.getTitle(), TestData.ERR_TITLE_MISMATCH);
                Assert.assertEquals(response.getArticle().getDescription(), articleData.getDescription(), TestData.ERR_DESCRIPTION_MISMATCH);
                Assert.assertEquals(response.getArticle().getBody(), articleData.getBody(), TestData.ERR_BODY_MISMATCH);
                Assert.assertEquals(response.getArticle().getTagList(), articleData.getTagList(), "Validation Failed: Embedded tag list elements mismatch!");
                Assert.assertEquals(response.getArticle().getAuthor().getUsername(), username, "Validation Failed: Response author context relationship mismatch!");
        }

        @Test(priority = 3, dependsOnMethods = { "createNewArticleAndVerifyPayload" }, 
              description = "Should successfully read active article details via its resource slug identifier")
        public void readArticleDetailsBySlug() {
                // Act
                PostArticleResponse response = executeGet(authSpec(token), "/articles/" + slug, TestData.STATUS_OK)
                                .as(PostArticleResponse.class);

                // Assert
                Assert.assertEquals(response.getArticle().getSlug(), slug, "Validation Failed: Extracted GET path slug identifier parameter mismatch!");
        }

        @Test(priority = 4, dependsOnMethods = { "createNewArticleAndVerifyPayload" }, 
              description = "Should successfully update an existing article and verify changes reflect accurately")
        public void updateArticleContentAndVerifyChanges() {
                // Arrange
                RequestArticle updatedData = new RequestArticle()
                                .setTitle("Updated Title " + System.currentTimeMillis())
                                .setDescription("Updated testing context metrics description")
                                .setBody("This article text content was modified cleanly via an automated HTTP PUT request pipeline action.")
                                .setTagList(List.of("Java", "RestAssured", "Enterprise"));

                PostArticleRequest updatedPayload = new PostArticleRequest().setArticle(updatedData);

                // Act
                PostArticleResponse response = executePut(authSpec(token), updatedPayload, "/articles/" + slug, TestData.STATUS_OK)
                                .as(PostArticleResponse.class);

                // Assert & Dynamic State Synchronization
                Assert.assertNotNull(slug, "Validation Failed: Modified response resource identifier slug returned null!");
                Assert.assertEquals(response.getArticle().getTitle(), updatedData.getTitle(), TestData.ERR_TITLE_MISMATCH);
                Assert.assertEquals(response.getArticle().getDescription(), updatedData.getDescription(), TestData.ERR_DESCRIPTION_MISMATCH);
                Assert.assertEquals(response.getArticle().getBody(), updatedData.getBody(), TestData.ERR_BODY_MISMATCH);

                // State Synchronization: ONLY update the global pointer after all assertions pass!
                // If an assertion above fails, 'slug' remains the old slug, allowing @AfterClass to clean it up safely.
                slug = response.getArticle().getSlug();
        }

        @Test(priority = 5, dependsOnMethods = { "createNewArticleAndVerifyPayload" }, 
              description = "Should successfully delete an active article resource via its active tracking slug")
        public void deleteArticleAndVerifyRemoval() {
                // Act
                executeDelete(authSpec(token), "/articles/" + slug, TestData.STATUS_NO_CONTENT);
                
                // Reset pointer context following successful direct removal sequence
                slug = null;
        }

        @AfterClass(alwaysRun = true)
        public void cleanUpOrphanedData() {
                // Protects your runtime cloud testing database from data pollution spikes
                if (slug != null && token != null) {
                        executeDelete(authSpec(token), "/articles/" + slug, TestData.STATUS_NO_CONTENT);
                }
        }
}
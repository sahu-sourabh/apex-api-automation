package com.apex.api.utils;

/**
 * Enterprise Constants Registry for HTTP Protocol and Global Validations
 */
public final class TestData {
    
    // Prevent instantiation of a utility constant class
    private TestData() {}

    // HTTP Status Codes
    public static final int STATUS_OK = 200;
    public static final int STATUS_CREATED = 201;
    public static final int STATUS_NO_CONTENT = 204;
    public static final int STATUS_UNPROCESSABLE_ENTITY = 422;

    // Standardized Core Assertion Messages
    public static final String ERR_TITLE_MISMATCH = "Validation Failed: Response body 'title' field mismatch!";
    public static final String ERR_BODY_MISMATCH = "Validation Failed: Response body 'body' field mismatch!";
    public static final String ERR_DESCRIPTION_MISMATCH = "Validation Failed: Response body 'description' field mismatch!";
    public static final String ERR_SLUG_NULL = "Validation Failed: Database failed to generate an entity slug identifier!";
}
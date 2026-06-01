package com.apex.api.utils;

public class TestData {
    // Post Creation Test Inputs
    public static final String POST_TITLE = "Apex Framework Title";
    public static final String POST_BODY = "This is an automated body text.";
    public static final int POST_USER_ID = 101;

    // Post Update Test Inputs
    public static final String UPDATE_TITLE = "Updated Title";
    public static final String UPDATE_BODY = "Updated body text.";

    // Assertion Error Messages (Standardized)
    public static final String ERR_TITLE_MISMATCH = "Validation Failed: Response body 'title' field mismatch!";
    public static final String ERR_BODY_MISMATCH = "Validation Failed: Response body 'body' field mismatch!";
    public static final String ERR_ID_NULL = "Validation Failed: Database failed to generate an entity ID!";

    // Mixed Data
    public static final int ONE = 1;
    public static final int TWO_HUNDRED = 200;
    public static final int TWO_HUNDRED_ONE = 201;
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String BODY = "body";
}
package com.apex.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        // Try-with-resources ensures the file stream is safely closed automatically
        try (FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("CRITICAL: Failed to load config.properties file configuration.", e);
        }
    }

    /**
     * Enterprise Cascading Lookup: Checks Environment variables first, falls back to property file.
     * Example: "base.url" checks for environment variable "BASE_URL" first.
     */
    public static String getProperty(String key) {
        String envKey = key.toUpperCase().replace(".", "_");
        String envValue = System.getenv(envKey);

        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        return properties.getProperty(key);
    }
}
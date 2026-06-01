package com.apex.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Locate the properties configuration file
            String filePath = "src/test/resources/config.properties";
            FileInputStream inputStream = new FileInputStream(filePath);

            // Load the file data into the Properties object
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Critical Error: Could not load config.properties file! " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

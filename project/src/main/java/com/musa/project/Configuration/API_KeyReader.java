package com.musa.project.Configuration;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class API_KeyReader {

    private static final String  API_KEY_PROPERTY = "PROFANITY_FILTER_API_KEY";

    public static String getApiKey() {
        Properties prop = new Properties();
        try(InputStream input = new ClassPathResource("api_key.properties").getInputStream()) {
            prop.load(input);
            return prop.getProperty(API_KEY_PROPERTY);
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

package com.musa.project.Security;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class SecretKeyReader {
    private static final String  SECRET_KEY_PROPERTY = "secret_key";

    public static String getSecretKeyProperty() {
        Properties prop = new Properties();
        try(InputStream input = new ClassPathResource("jwt_secret_key.properties").getInputStream()) {
            prop.load(input);
            return Optional.ofNullable(prop.getProperty(SECRET_KEY_PROPERTY))
                    .orElseThrow(() -> new IllegalStateException("JWT secret key not found"));

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}

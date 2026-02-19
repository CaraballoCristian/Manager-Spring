package com.musa.project.Product;

import com.musa.project.Exceptions.ProfanityFilterException;
import com.musa.project.Exceptions.ProfanityValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProfanityValidatorTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProfanityValidator profanityValidator;


    // ---- RETURNS HAS PROFANITY ----
    @Test
    void profanityValidatorTest_returnsHasProfanity(){
        // Arrange
        ProfanityFilterApiResponse response = new ProfanityFilterApiResponse(true);

        when(restTemplate.exchange(
                    anyString(),
                    eq(HttpMethod.GET),
                    any(HttpEntity.class),
                    eq(ProfanityFilterApiResponse.class)
                )
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // Assert
        assertTrue(profanityValidator.hasProfanity(new ProductRequest(
                "testName",
                "testDescription",
                "testManufacturer",
                null, null, null)
                )
        );
    }

    // ---- RETURNS HAS NO PROFANITY ----
    @Test
    void profanityValidatorTest_returnsHasNoProfanity(){
        // Arrange
        ProfanityFilterApiResponse response = new ProfanityFilterApiResponse(false);

        when(restTemplate.exchange(
                        anyString(),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(ProfanityFilterApiResponse.class)
                )
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // Assert
        assertFalse(profanityValidator.hasProfanity(new ProductRequest(
                        "testName",
                        "testDescription",
                        "testManufacturer",
                        null, null, null)
                )
        );
    }

    // ---- THROWS EXCEPTION ----
    @Test
    void profanityValidatorTest_throwsException(){
        // Arrange
        when(restTemplate.exchange(
                        anyString(),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(ProfanityFilterApiResponse.class)
                )
        ).thenThrow(new RuntimeException());

        // Assert
        assertThrows(ProfanityFilterException.class,
                () -> profanityValidator.hasProfanity(new ProductRequest(
                    "testName",
                    "testDescription",
                    "testManufacturer",
                    null, null, null)
                )
        );
    }
}

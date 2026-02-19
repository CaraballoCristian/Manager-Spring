package com.musa.project.product;

import com.musa.project.exceptions.ProfanityFilterException;
import com.musa.project.exceptions.ProfanityValidator;
import com.musa.project.product.dto.ProductRequestDTO;
import com.musa.project.product.dto.ProfanityFilterApiResponseDTO;
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
        ProfanityFilterApiResponseDTO response = new ProfanityFilterApiResponseDTO(true);

        when(restTemplate.exchange(
                    anyString(),
                    eq(HttpMethod.GET),
                    any(HttpEntity.class),
                    eq(ProfanityFilterApiResponseDTO.class)
                )
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // Assert
        assertTrue(profanityValidator.hasProfanity(new ProductRequestDTO(
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
        ProfanityFilterApiResponseDTO response = new ProfanityFilterApiResponseDTO(false);

        when(restTemplate.exchange(
                        anyString(),
                        eq(HttpMethod.GET),
                        any(HttpEntity.class),
                        eq(ProfanityFilterApiResponseDTO.class)
                )
        ).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        // Assert
        assertFalse(profanityValidator.hasProfanity(new ProductRequestDTO(
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
                        eq(ProfanityFilterApiResponseDTO.class)
                )
        ).thenThrow(new RuntimeException());

        // Assert
        assertThrows(ProfanityFilterException.class,
                () -> profanityValidator.hasProfanity(new ProductRequestDTO(
                    "testName",
                    "testDescription",
                    "testManufacturer",
                    null, null, null)
                )
        );
    }
}

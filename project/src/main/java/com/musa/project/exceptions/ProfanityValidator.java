package com.musa.project.Exceptions;

import com.musa.project.Configuration.API_KeyReader;
import com.musa.project.Product.ProductRequest;
import com.musa.project.Product.ProfanityFilterApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@Component
public class ProfanityValidator {

    public final RestTemplate restTemplate;

    // ---- GET API KEY ----
    private static final String API_KEY = API_KeyReader.getApiKey();

    // ---- PROFANITY CHECKER ----
    public boolean hasProfanity(ProductRequest request){

        // ---- HEADERS ----
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        try{
            // ---- API CALL ----
            ResponseEntity<ProfanityFilterApiResponse> responseEntity = restTemplate.exchange(
                    "https://api.api-ninjas.com/v1/profanityfilter?text="
                            + request.getName() + " "
                            + request.getDescription() + " "
                            + request.getManufacturer(),
                        HttpMethod.GET, // Setting method
                        entity, // Setting Headers
                        ProfanityFilterApiResponse.class // Setting response format
                    );

            // ---- LOGGER ----
            log.info("ProfanityValidator - Response: {}", responseEntity.getBody());

            return  responseEntity.getBody().getHas_profanity();

        } catch (Exception e) {
            throw new ProfanityFilterException();
        }
    }

}

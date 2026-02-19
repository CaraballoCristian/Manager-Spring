package com.musa.project.integration.profanity.dto;

import lombok.Data;

@Data
public class ProfanityFilterApiResponseDTO {
    private String original;
    private String censored;
    private Boolean has_profanity;

    public ProfanityFilterApiResponseDTO(Boolean has_profanity) {
        this.has_profanity = has_profanity;
    }
}

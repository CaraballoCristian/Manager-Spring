package com.musa.project.Product;

import lombok.Data;

@Data
public class ProfanityFilterApiResponse {
    private String original;
    private String censored;
    private Boolean has_profanity;

    public ProfanityFilterApiResponse(Boolean has_profanity) {
        this.has_profanity = has_profanity;
    }
}

package com.musa.project.configuration;

public enum E_CacheName {
    PRODUCTS("products");

    private final String value;

    E_CacheName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

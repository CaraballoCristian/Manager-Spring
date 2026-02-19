package com.musa.project.utils;

import org.springframework.http.ResponseEntity;

// Its used when we are mutating Data from the database
public interface Command <I,O>{
    ResponseEntity<O> execute(I input);
}

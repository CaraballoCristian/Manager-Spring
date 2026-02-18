package com.musa.project;

import com.musa.project.Product.ProductDTO;
import org.springframework.http.ResponseEntity;

// Its used just for getting information from the database
public interface Query <I,O>{
    ResponseEntity<O> execute(I input);
}

package com.oriontek.tecnical_test.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedDataException extends RuntimeException{
    public DuplicatedDataException(String message) {
        super(message);
    }
}

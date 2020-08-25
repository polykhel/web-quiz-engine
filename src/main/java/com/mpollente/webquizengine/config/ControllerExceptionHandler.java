package com.mpollente.webquizengine.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "Information not found";

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NOT_FOUND_MESSAGE)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", NOT_FOUND_MESSAGE);
        response.put("error", e.getClass().getSimpleName());
        return response;
    }

    @ExceptionHandler({ConstraintViolationException.class, org.hibernate.exception.ConstraintViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid data")
    public Map<String, String> handleConstraintViolationException(Exception e) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Not valid due to validation error: " + e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}

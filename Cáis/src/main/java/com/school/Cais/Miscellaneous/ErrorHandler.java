package com.school.Cais.Miscellaneous;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    public static <T> T alreadyExists(String topic){
        return throwError(topic + " already exists", HttpStatus.CONFLICT);
    }

    public static <T> T notFound(String topic){
        return throwError(topic + " not found", HttpStatus.NOT_FOUND);
    }

    public static <T> T soldOut(String topic, int amount){
        return throwError("There are only " + amount + " products \"" + topic + "\" left in stock", HttpStatus.CONFLICT);
    }

    private static <T> T throwError(String message, HttpStatus status) {
        message = message.substring(0, 1).toUpperCase() + message.substring(1);
        ResponseStatusException ex = new ResponseStatusException(status, message);
        System.out.println(ex.getMessage());
        throw new ResponseStatusException(status, message);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getReason());
        body.put("time stamp", Instant.now());
        return new ResponseEntity<>(body, ex.getStatusCode());
    }
}

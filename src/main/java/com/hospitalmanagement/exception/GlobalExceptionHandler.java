package com.hospitalmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =========================
    // VALIDATION ERRORS
    // =========================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(
            MethodArgumentNotValidException exception
    ) {

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        Map.of(
                                "message",
                                "Validation failed",

                                "errors",
                                errors
                        )
                );
    }

    // =========================
    // ILLEGAL ARGUMENT
    // =========================

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {

        return ResponseEntity
                .badRequest()
                .body(
                        Map.of(
                                "message",
                                exception.getMessage() != null
                                        ? exception.getMessage()
                                        : "Invalid request"
                        )
                );
    }

    // =========================
    // RUNTIME EXCEPTION
    // =========================

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(
            RuntimeException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        Map.of(
                                "message",
                                exception.getMessage() != null
                                        ? exception.getMessage()
                                        : "Request failed"
                        )
                );
    }

    // =========================
    // GENERIC EXCEPTION
    // =========================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(
            Exception exception
    ) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "message",
                                "Something went wrong on the server."
                        )
                );
    }
}
package com.cash.assignment.Exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public ResponseEntity<?> handleAll(Exception e, WebRequest request) {
    ApiError apiError = ApiError.builder()
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .message(e.getMessage())
        .errors(request.getDescription(false))
        .build();

    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public Map<String, String> handleValidArgument(MethodArgumentNotValidException e,
      WebRequest request) {
    Map<String, String> error = new HashMap<>();
    e.getBindingResult().getFieldErrors().forEach(err -> {
      error.put(err.getField(), err.getDefaultMessage());
    });
    return error;
  }
}

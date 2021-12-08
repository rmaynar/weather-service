package com.globant.weatherservice.advice;

import com.globant.weatherservice.common.CustomErrorResponse;
import com.globant.weatherservice.exception.ResourceAlreadyExistingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {

  private static final String PARSE_DATA_ERROR = "PARSE_DATA_ERROR";
  private static final String EXISTING_DATA_ERROR = "EXISTING_DATA_ERROR";

  @ExceptionHandler(value = ParseException.class)
  public ResponseEntity<CustomErrorResponse> handleGenericParseException() {

    var error = new CustomErrorResponse(PARSE_DATA_ERROR, "Error while parsing data.");
    error.setTimestamp(LocalDateTime.now());
    error.setStatus((HttpStatus.NOT_FOUND.value()));
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = ResourceAlreadyExistingException.class)
  public ResponseEntity<CustomErrorResponse> handleGenericParseException(
      ResourceAlreadyExistingException e) {

    var error = new CustomErrorResponse(EXISTING_DATA_ERROR, e.getMessage());
    error.setTimestamp(LocalDateTime.now());
    error.setStatus((HttpStatus.BAD_REQUEST.value()));
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}

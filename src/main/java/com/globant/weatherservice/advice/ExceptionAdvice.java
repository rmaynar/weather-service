package com.globant.weatherservice.advice;

import com.globant.weatherservice.common.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.ParseException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<CustomErrorResponse> handleGenericParseException(ParseException e){
        CustomErrorResponse error = new CustomErrorResponse("PARSE_DATA_ERROR", "Error while parsing data.");
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.NOT_FOUND.value()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}

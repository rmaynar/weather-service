package com.globant.weatherservice.exception;

public class ResourceAlreadyExistingException extends RuntimeException {

  public ResourceAlreadyExistingException(String msg) {
    super(msg);
  }
}

package com.ptit.util;

import org.springframework.http.HttpStatus;


public class ApiMessage {

  private HttpStatus status;
  private String message;

  public ApiMessage(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}

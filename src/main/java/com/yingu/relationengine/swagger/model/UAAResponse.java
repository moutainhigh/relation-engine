package com.yingu.relationengine.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/**
 * SuccessModel
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-02-22T09:51:09.457Z")

public class UAAResponse {

//  "email address already in use", textPlainHeaders, HttpStatus.BAD_REQUEST
  @JsonProperty("message")
  private String message;

  @JsonProperty("httpHeaders")
  private HttpHeaders httpHeaders;

  @JsonProperty("status")
  private HttpStatus status;


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpHeaders getHttpHeaders() {
    return httpHeaders;
  }

  public void setHttpHeaders(HttpHeaders httpHeaders) {
    this.httpHeaders = httpHeaders;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }
}


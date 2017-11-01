package com.yingu.relationengine.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * ErrorModel
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-09T02:21:06.504Z")

public class ErrorModel {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("message")
  private String message = null;

//  @JsonProperty("data")
//  private Object data = null;


  private Date    timestamp = new Date();
  private int     status;
  private String  error;
  private String  exception;
  private String  path;

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getException() {
    return exception;
  }

  public void setException(String exception) {
    this.exception = exception;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public ErrorModel code(Integer code) {
    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(required = true, value = "")
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public ErrorModel message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(required = true, value = "")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

//  public ErrorModel data(Object data) {
//    this.data = data;
//    return this;
//  }

   /**
   * Get data
   * @return data
  **/
//  @ApiModelProperty(value = "")
//  public Object getData() {
//    return data;
//  }
//
//  public void setData(Object data) {
//    this.data = data;
//  }


//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    ErrorModel errorModel = (ErrorModel) o;
//    return Objects.equals(this.code, errorModel.code) &&
//        Objects.equals(this.message, errorModel.message) &&
//        Objects.equals(this.data, errorModel.data);
//  }

//  @Override
//  public int hashCode() {
//    return Objects.hash(code, message, data);
//  }

//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("class ErrorModel {\n");
//
//    sb.append("    code: ").append(toIndentedString(code)).append("\n");
//    sb.append("    message: ").append(toIndentedString(message)).append("\n");
//    sb.append("    data: ").append(toIndentedString(data)).append("\n");
//    sb.append("}");
//    return sb.toString();
//  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


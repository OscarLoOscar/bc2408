package com.bootcamp.demo_restapi.exception;

import lombok.Getter;

@Getter
public enum Code {
  OK(0, "OK"), //
  INVALID_INPUT(9, "Invalid input"), //
  DIVIDE_BY_ZERO(10, "Divide by zero"), //
  INVALID_OPERATION(11, "Invalid operation");

  private int code;
  private String message;

  private Code(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static Code fromCode(int code) {
    for (Code c : Code.values()) {
      if (c.getCode() == code) {
        return c;
      }
    }
    return null;
  }

}

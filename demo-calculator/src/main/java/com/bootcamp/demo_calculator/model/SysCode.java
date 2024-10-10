package com.bootcamp.demo_calculator.model;

import lombok.Getter;

@Getter
public enum SysCode {
  OK(0, "OK"), //
  INVALID_INPUT(9, "Invalid input"), //
  DIVIDE_BY_ZERO(10, "Divide by zero"), //
  INVALID_OPERATION(11, "Invalid operation");

  private int code;
  private String message;

  private SysCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}

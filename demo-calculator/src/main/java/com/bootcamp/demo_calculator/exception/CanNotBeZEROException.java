package com.bootcamp.demo_calculator.exception;

import lombok.Getter;

@Getter
public class CanNotBeZEROException extends IllegalArgumentException {
  private String message;

  public CanNotBeZEROException(String message) {
    this.message = message;
  }
}

package com.bootcamp.demo_calculator.exception;

import lombok.Getter;

@Getter
public class InvalidOperatorException extends IllegalArgumentException {
  private String message;


  public InvalidOperatorException(String message) {
    this.message = message;
  }
}

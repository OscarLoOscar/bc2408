package com.bootcamp.demo_restapi.exception;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
  private Code code;

  public BusinessException(Code code) {
    super(code.getMessage());
    this.code = code;
  }
}

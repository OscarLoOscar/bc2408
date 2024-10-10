package com.bootcamp.demo_restapi.exception;

public class InvalidInputException extends BusinessException{
  
  public InvalidInputException(Code message) {
    super(message);
  }
}

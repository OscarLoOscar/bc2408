package com.bootcamp.bc_calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.bc_calculator.model.ErrorResponse;

public class GlobalExceptionHandler {
  // 全局捕捉Exception-> Server 有error，GlobalExceptionHandler 幫你處理
  // 唔洗try catch ，唔要method signature throw exception
  // keep all your method clean

  @ExceptionHandler(CannotDivideZeroException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse cannotDivideZeroException() {
    return ErrorResponse.builder()//
        .code(9)//
        .message("Invalid Input")//
        .build();
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse runtimeException() {
    return ErrorResponse.builder()//
        .code(9)//
        .message("RuntimeException ")//
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse exception() {
    return ErrorResponse.builder()//
        .code(9)//
        .message("Invalid Input")//
        .build();
  }
}

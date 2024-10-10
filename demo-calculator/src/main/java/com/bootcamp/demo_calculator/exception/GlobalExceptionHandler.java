package com.bootcamp.demo_calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo_calculator.model.ApiResponse;
import com.bootcamp.demo_calculator.model.SysCode;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = CalculateException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> calculateException() {
    return ApiResponse.<Void>builder()//
        .code(String.valueOf(SysCode.DIVIDE_BY_ZERO.getCode()))//
        .message(SysCode.DIVIDE_BY_ZERO.getMessage())//
        .build();
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> calculateException2() {
    return ApiResponse.<Void>builder()//
        .code(String.valueOf(SysCode.INVALID_INPUT.getCode()))//
        .message(SysCode.INVALID_INPUT.getMessage())//
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResponse<Void> calculateException3() {
    return ApiResponse.<Void>builder()//
        .code(String.valueOf(SysCode.INVALID_OPERATION.getCode()))//
        .message(SysCode.INVALID_OPERATION.getMessage())//
        .build();
  }

 
// {
// "code" : 200,
// "message" : "OK",
// "data" : [

// ],
// "error" : [
// "", ""
// ],
// }

}
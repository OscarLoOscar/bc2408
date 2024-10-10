package com.bootcamp.demo_restapi.exception;

import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  //   @ExceptionHandler(value = BusinessException.class)
  //   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  //   public ApiResp<Void> finnhubExceptionHandler(BusinessException e) {
  //     return ApiResp.<Void>builder() //
  //         .status(Code.fromCode(e.getCode().getCode())) //
  //         .concatMessageIfPresent(e.getMessage())//
  //         // .data(null) //
  //         .build();
  //   }



  //   @ExceptionHandler(value = InvalidInputException.class)
  //   @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  //   public ApiResp<Void> finnhubExceptionHandler(InvalidInputException e) {
  //     return ApiResp.<Void>builder() //
  //       .status(getRespCode(e))//
  //       .concatMessageIfPresent(e.getMessage())//
  //       // .data(null) //
  //       .build();
  // }

  // @ExceptionHandler(value = RuntimeException.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
  //   return ApiResp.<Void>builder() //
  //       .status(getRespCode(e)) //
  //       .concatMessageIfPresent(e.getMessage())//
  //       // .data(null) //
  //       .build();
  // }

  // @ExceptionHandler(value = Exception.class)
  // @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  // public ApiResp<Void> exceptionHandler(Exception e) {
  //   return ApiResp.<Void>builder() //
  //       .status(getRespCode(e)) //
  //       .concatMessageIfPresent(e.getMessage())//
  //       // .data(null) //
  //       .build();
  // }

  // private static Code getRespCode(Exception e) {
  //   if (e instanceof NoSuchElementException) {
  //     return Code.INVALID_INPUT;
  //   }
  //   if (e instanceof IllegalArgumentException) {
  //     return Code.INVALID_INPUT;
  //   }
  //   if (e instanceof ArithmeticException) {
  //     return Code.DIVIDE_BY_ZERO;
  //   }

  //   // ...
  //   // return null;
  //   return Code.INVALID_OPERATION;
  // }
}

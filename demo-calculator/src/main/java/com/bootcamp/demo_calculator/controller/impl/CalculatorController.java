package com.bootcamp.demo_calculator.controller.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_calculator.controller.CalculatorOperation;
import com.bootcamp.demo_calculator.model.ApiResponse;
import com.bootcamp.demo_calculator.model.CalculateRequest;
import com.bootcamp.demo_calculator.model.CalculateResponse;
import com.bootcamp.demo_calculator.model.SysCode;
import com.bootcamp.demo_calculator.service.impl.CalculatorService;

@RestController
public class CalculatorController implements CalculatorOperation {

  @Autowired
  private CalculatorService calculatorService;

  @Override
  public CalculateResponse calculate(String x, String y,
      String operation) {
    return calculatorService.calculate(//
            BigDecimal.valueOf(Long.valueOf(x)), //
            BigDecimal.valueOf(Long.valueOf(y)), //
            operation);

  }

  @Override
  public CalculateResponse calculate2(String x, String y,
      String operation) {
    return calculatorService.calculate(//
            BigDecimal.valueOf(Long.valueOf(x)), //
            BigDecimal.valueOf(Long.valueOf(y)), //
            operation);
  }

  @Override
  public ApiResponse<CalculateResponse> calculate(
      CalculateRequest calculateRequest) {
    return ApiResponse.<CalculateResponse>builder()//
        .code(String.valueOf(SysCode.OK.getCode()))//
        .message(SysCode.OK.getMessage())//
        .data(calculatorService.calculate(//
            BigDecimal.valueOf(Long.valueOf(calculateRequest.getX())), //
            BigDecimal.valueOf(Long.valueOf(calculateRequest.getY())), //
            calculateRequest.getOperation()))//
        .build();
  }

}

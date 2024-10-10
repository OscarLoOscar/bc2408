package com.bootcamp.demo_calculator.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.bootcamp.demo_calculator.exception.CalculateException;
import com.bootcamp.demo_calculator.model.CalculateResponse;
import com.bootcamp.demo_calculator.model.Operation;
import com.bootcamp.demo_calculator.service.impl.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

  @Override
  public CalculateResponse calculate(BigDecimal x, BigDecimal y,
  String operation) {
        Operation operator = this.map(operation);
    return CalculateResponse.builder()//
        .x(String.valueOf(x))//
        .y(String.valueOf(y))//
        .operation(operator.name())//
        .result(operator.calculate(x, y))//
        .build();
  }

  @Override
  public CalculateResponse calculate2(BigDecimal x, BigDecimal y,
  String operation) {
    Operation operator = this.map(operation);

    return CalculateResponse.builder()//
        .x(String.valueOf(x))//
        .y(String.valueOf(y))//
        .operation(operator.name())//
        .result(operator.calculate(x, y))//
        .build();
  }
  // String -> Operation.valueOf(operation) -> fit Operation attribute

  private Operation map(String operation) {

    for (Operation op : Operation.values()) {
      if (op.getOperator().equals(operation)) {
        return op;
      }
    }
    throw new CalculateException("Illegal input operator");
  }

}

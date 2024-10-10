package com.bootcamp.bc_calculator.model;

import java.math.BigDecimal;
import com.bootcamp.bc_calculator.exception.CannotDivideZeroException;
import lombok.Getter;

@Getter
public enum Operator {
  ADD("add"), //
  SUBTRACT("sub"), //
  MULTIPLY("mul"), //
  DIVIDE("div"),//
  ;

  private String operator;

  private Operator(String operator) {
    this.operator = operator;
  }

  public String calculate(BigDecimal x , BigDecimal y) {
    return switch(this){
      case ADD -> x.add(y).setScale(5).toString();
      case SUBTRACT-> x.subtract(y).setScale(5).toString();
      case MULTIPLY-> x.multiply(y).setScale(5).toString();
      case DIVIDE ->{
        // DIVIDE 0 -> Exception
        if(y.equals(BigDecimal.ZERO)){
          throw new CannotDivideZeroException("Can not input ZERO");
        }
        yield x.divide(y).setScale(5).toString();
      }
    };
    
  }
}

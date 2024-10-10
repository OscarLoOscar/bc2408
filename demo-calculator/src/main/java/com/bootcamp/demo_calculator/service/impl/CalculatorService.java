package com.bootcamp.demo_calculator.service.impl;

import java.math.BigDecimal;
import com.bootcamp.demo_calculator.model.CalculateResponse;

public interface CalculatorService {
    CalculateResponse calculate(BigDecimal x, //
            BigDecimal y, //
            String operation);

    CalculateResponse calculate2(BigDecimal x, //
            BigDecimal y, //
            String operation);

}

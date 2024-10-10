package com.bootcamp.bc_calculator.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_calculator.model.CalculateRequest;
import com.bootcamp.bc_calculator.model.CalculateResponse;

public interface CalculatorOperation {
  // method 1 : calculate(String x ,String y ,String operation);
  // method 2 : calculate(String x ,String y ,String operation);
  // polymorphism -> method overloading
  // same method signature can not accept same number of input parma and same type input param

  // 1.RequestParam
  // 2.PathVariable
  // 3.RequestBody -> Object -> send JSON to server

  @GetMapping("/operation")
  CalculateResponse calculate(@RequestParam String x, //
      @RequestParam String y, //
      @RequestParam String operation);

  @PostMapping("/operation")
  CalculateResponse calculate(@RequestBody CalculateRequest calculateRequest);

  @GetMapping("/operation/{x}/{y}/{operation}")
  CalculateResponse calculate2(@PathVariable String x, //
      @PathVariable String y, //
      @PathVariable String operation);

}

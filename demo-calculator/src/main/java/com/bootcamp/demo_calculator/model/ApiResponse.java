package com.bootcamp.demo_calculator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null 既野唔show出黎
public class ApiResponse<T> {
  private String code;
  private String message;
  private T data;
}

// {
//   "code" : 200,
//   "message": OK,
//   "data" : {
//     "x": "5",
//     "y" : "10",
//     "operation" : "add",
//     "result" : "15"
//   }
//

  //   "code" : 400,
  //   "message": FAIL

  

package com.example;

import java.util.List;

public class IntergerOperationManager {
  private List<Integer> integers;

  public IntergerOperationManager(List<Integer> integers) {
    this.integers = integers;
  }

  public int sum() {
    int sum = 0;
    for (Integer i : this.integers) {
      sum += i;
    }
    return sum;
  }
}

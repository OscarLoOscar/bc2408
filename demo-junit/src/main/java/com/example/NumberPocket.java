package com.example;

import java.util.ArrayList;
import java.util.List;

public class NumberPocket {
  private List<Integer> integers;
  private IntergerOperationManager intergerOperationManager;

  public NumberPocket(IntergerOperationManager intergerOperationManager) {
    integers = new ArrayList<>();
    this.intergerOperationManager = intergerOperationManager;
  }

  public void add(int i) {
    integers.add(i);
  }

  public int sum() {
    return this.intergerOperationManager.sum();
  }
}

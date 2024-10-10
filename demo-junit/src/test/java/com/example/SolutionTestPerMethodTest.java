package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class SolutionTestPerMethodTest {
  @BeforeAll
  public static void beforeAll() {
    System.out.println("Before all");
  }

  @AfterAll
  public static void afterAll() {
    System.out.println("After all");
  }

  @BeforeEach
  public void beforeEach() {
    System.out.println("Before each");
  }

  @AfterEach
  public void afterEach() {
    System.out.println("After each");
  }

  @Test
  public void test1() {
    System.out.println("Test 1");
  }

  @Test
  public void test2() {
    System.out.println("Test 2");
  }

}

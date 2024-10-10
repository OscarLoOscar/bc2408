package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SolutionTestPerClassTest {
  @BeforeAll
  public void beforeAll() {
    System.out.println("Before all...");
  }

  @AfterAll
  public void afterAll() {
    System.out.println("After all...");
  }

  @BeforeEach
  public void beforeEach() {
    System.out.println("Before each...");
  }

  @AfterEach
  public void afterEach() {
    System.out.println("After each...");
  }

  @Test
  @Order(2)
  public void test1() {
    System.out.println("Test 1");
  }

  @Test
  @Order(1)
  public void test2() {
    System.out.println("Test 2");
  }

}

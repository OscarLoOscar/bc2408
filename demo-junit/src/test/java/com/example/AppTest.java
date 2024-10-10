package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Unit test for simple App.
 */

public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @DisplayName("Test add")
    @ParameterizedTest
    @CsvSource({
        "1, 2, 3",//
        "2, 3, 5",//
        "3, 4, 7"//
    })
    public void testAdd(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, -1",//
        "2, 3, -1",//
        "3, 4, -1"//
    })
    public void testSubtract(int a, int b, int expected) {
        assertEquals(expected, a - b);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 2",//
        "2, 3, 6",//
        "3, 4, 12"//
    })
    public void testMultiply(int a, int b, int expected) {
        assertEquals(expected, a * b);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2, 0",//
        "2, 3, 0",//
        "3, 4, 0"//
    })
    public void testDivide(int a, int b, int expected) {
        assertEquals(expected, a / b);
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(Exception.class, () -> {
            App.divide(1, 0);
        });
        assertEquals("Cannot divide by zero", exception.getMessage());
    }
}

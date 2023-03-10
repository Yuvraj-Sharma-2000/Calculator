package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    /**
     * Method under test: {@link Main#calculator()}
     */
    @Test
    @Disabled("No method found to set main")
    void testCalculator() {
        // TODO: Complete this test.

        // Arrange and Act
        // TODO: Populate arranged inputs
        Main.calculator();

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link Main#getPow(double, double)}
     */
    @Test
    void testGetPow() {
        // Arrange, Act and Assert
        assertEquals(1.0E10d, Main.getPow(10.0d, 10.0d));
        assertEquals(9.765625E-4d, Main.getPow(0.5d, 10.0d));
        assertEquals(9.765625E-4d, Main.getPow(-0.5d, 10.0d));
        assertEquals(Double.NaN, Main.getPow(Double.NaN, 10.0d));
    }

    /**
     * Method under test: {@link Main#getLog(double)}
     */
    @Test
    void testGetLog() {
        // Arrange, Act and Assert
        assertEquals(2.302585092994046d, Main.getLog(10.0d));
    }

    /**
     * Method under test: {@link Main#getFactorial(int)}
     */
    @Test
    void testGetFactorial() {
        // Arrange, Act and Assert
        assertEquals(6, Main.getFactorial(3));
    }

    /**
     * Method under test: {@link Main#getSqrt(double)}
     */
    @Test
    void testGetSqrt() {
        // Arrange, Act and Assert
        assertEquals(3.1622776601683795d, Main.getSqrt(10.0d));
    }

}


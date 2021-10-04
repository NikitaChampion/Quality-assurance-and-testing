package root.gcd;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class GCDTest {
    public GCD gcd = new GCD();

    @Test
    void positiveTest() {
        assertEquals(1, gcd.gcd(1, 2));
        assertEquals(1, gcd.gcd(2, 1));

        assertEquals(2, gcd.gcd(2, 4));
        assertEquals(2, gcd.gcd(4, 2));

        assertEquals(4, gcd.gcd(4, 8));
        assertEquals(4, gcd.gcd(8, 4));
    }

    @Test
    void negativeTest() {
        assertEquals(1, gcd.gcd(-1, 2));
        assertEquals(1, gcd.gcd(-2, 1));

        assertEquals(2, gcd.gcd(2, -4));
        assertEquals(2, gcd.gcd(4, -2));

        assertEquals(4, gcd.gcd(-4, -8));
        assertEquals(4, gcd.gcd(-8, -4));
    }

    @Test
    void zeroTest() {
        assertEquals(1, gcd.gcd(0, 1));
        assertEquals(1, gcd.gcd(1, 0));

        assertEquals(2, gcd.gcd(0, -2));
        assertEquals(2, gcd.gcd(-2, 0));

        assertEquals(4, gcd.gcd(0, -4));
        assertEquals(4, gcd.gcd(-4, 0));
    }

    @Test
    void coprimeTest() {
        assertEquals(1, gcd.gcd(91, 99));
        assertEquals(1, gcd.gcd(91, 101));
        assertEquals(1, gcd.gcd(91, 103));
        assertEquals(1, gcd.gcd(91, 107));
        assertEquals(1, gcd.gcd(91, 109));
    }

    @Test
    void equalTest() {
        assertEquals(4, gcd.gcd(4, 4));

        assertEquals(2, gcd.gcd(-2, -2));

        assertEquals(10000, gcd.gcd(-10000, -10000));

        assertEquals(0, gcd.gcd(0, 0));
    }

    @Test
    void divisionByTest() {
        assertEquals(2, gcd.gcd(2, 8));
        assertEquals(2, gcd.gcd(8, 2));

        assertEquals(2, gcd.gcd(2, 1000));
        assertEquals(2, gcd.gcd(1000, 2));
    }

    @Test
    void notEqualTest() {
        assertEquals(3, gcd.gcd(6, 21));
        assertEquals(3, gcd.gcd(21, 6));

        assertEquals(2, gcd.gcd(4, 6));
        assertEquals(2, gcd.gcd(6, 4));
    }

    @Test
    void fibonacciTest() {
        // Fib[46], Fib[45]
        assertEquals(1, gcd.gcd(1836311903, 1134903170));
    }

    @Test
    void overflowTest() {
        assertEquals(Integer.MAX_VALUE, gcd.gcd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertTimeoutPreemptively(Duration.ofSeconds(2), () ->
                assertEquals(1, gcd.gcd(Integer.MIN_VALUE, Integer.MAX_VALUE)));
        assertTimeoutPreemptively(Duration.ofSeconds(2), () ->
                assertEquals(1, gcd.gcd(Integer.MAX_VALUE, Integer.MIN_VALUE)));
        assertTimeoutPreemptively(Duration.ofSeconds(2), () ->
                assertEquals((long) Math.pow(2, 31), gcd.gcd(Integer.MIN_VALUE, Integer.MIN_VALUE)));
    }
}

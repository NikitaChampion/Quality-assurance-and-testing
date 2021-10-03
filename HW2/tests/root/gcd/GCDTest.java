package root.gcd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GCDTest {
    public GCD gcdInstance = new GCD();
    public NewGCD newGCDInstance = new NewGCD();

    @Test
    void positiveTest() {
        for (int x = 1; x <= 1000; ++x) {
            for (int y = 1; y <= 1000; ++y) {
                assertEquals(gcdInstance.gcd(x, y), newGCDInstance.gcd(x, y));
            }
        }
    }

    @Test
    void negativeTest() {
        for (int x = 1; x <= 1000; ++x) {
            for (int y = 1; y <= 1000; ++y) {
                assertEquals(gcdInstance.gcd(-x, -y), newGCDInstance.gcd(-x, -y));
                assertEquals(gcdInstance.gcd(-x, y), newGCDInstance.gcd(-x, y));
                assertEquals(gcdInstance.gcd(x, -y), newGCDInstance.gcd(x, -y));
            }
        }
    }

    @Test
    void zeroTest() {
        for (int i = Integer.MIN_VALUE + 1; i <= Integer.MAX_VALUE - 1; ++i) {
            if (i > 0) {
                assertEquals(gcdInstance.gcd(0, i), i);
                assertEquals(gcdInstance.gcd(0, i), i);
            } else {
                assertEquals(gcdInstance.gcd(i, 0), -i);
                assertEquals(gcdInstance.gcd(i, 0), -i);
            }
        }
    }

    @Test
    void primeTest() {
        for (int x = 1; x <= 1000; ++x) {
            for (int y = 1; y <= 1000; ++y) {
                if (newGCDInstance.gcd(x, y) == 1) {
                    assertEquals(gcdInstance.gcd(x, y), 1);
                }
            }
        }
    }

    @Test
    void equalTest() {
        for (int i = Integer.MIN_VALUE + 1; i <= Integer.MAX_VALUE - 1; ++i) {
            if (i >= 0) {
                assertEquals(gcdInstance.gcd(i, i), i);
            } else {
                assertEquals(gcdInstance.gcd(i, i), -i);
            }
        }
    }

    @Test
    void notEqualDivTest() {
        for (int i = -1000; i <= 1000; ++i) {
            if (i == 0) {
                continue;
            }
            for (int j = -1000; j <= 1000; ++j) {
                if (j == 0 || j == 1) {
                    continue;
                }
                if (i * j / j == i) {
                    if (i >= 0) {
                        assertEquals(gcdInstance.gcd(i * j, i), i);
                        assertEquals(gcdInstance.gcd(i, i * j), i);
                    } else {
                        assertEquals(gcdInstance.gcd(i * j, i), -i);
                        assertEquals(gcdInstance.gcd(i, i * j), -i);
                    }
                }
            }
        }
    }

    @Test
    void notEqualTest() {
        int k = 2;
        for (int i = -1000; i <= 1000; ++i) {
            if (i == 0) {
                continue;
            }
            for (int j = -1000; j <= 1000; ++j) {
                if (j == 0 || i == j) {
                    continue;
                }
                assertTrue(gcdInstance.gcd(k * i, k * j) > 1);
            }
        }
    }

    @Test
    void overflowTest() {
        for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE - 1; ++i) {
            assertEquals(gcdInstance.gcd(Integer.MIN_VALUE, i),
                    newGCDInstance.gcd(Integer.MIN_VALUE, i));
            assertEquals(gcdInstance.gcd(i, Integer.MIN_VALUE),
                    newGCDInstance.gcd(i, Integer.MIN_VALUE));
        }
        for (int i = Integer.MIN_VALUE; i <= Integer.MAX_VALUE - 1; ++i) {
            assertEquals(gcdInstance.gcd(Integer.MAX_VALUE, i),
                    newGCDInstance.gcd(Integer.MAX_VALUE, i));
            assertEquals(gcdInstance.gcd(i, Integer.MAX_VALUE),
                    newGCDInstance.gcd(i, Integer.MAX_VALUE));
        }
    }
}

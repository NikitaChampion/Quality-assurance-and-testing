package root.pow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerTest {
    Power power = new Power();

    @Test
    void zeroPowTest() {
        for (int i = -100_000; i <= 100_000; ++i) {
            if (i != 0) {
                assertNotEquals(power.pow(i, 1), 0);
            }
        }
    }

    @Test
    void isPowTest() {
        for (int i = 0; i <= (1 << 15); ++i) {
            assertEquals(power.pow(i, 2), Math.pow(i, 2));
        }
    }

    @Test
    void intMaxPowTest() {
        assertEquals(power.pow(2, 31), Math.pow(2, 31));
    }

    @Test
    void modulePowTest() {
        for (int i = -100_000; i <= 100_000; i++) {
            assertEquals(power.pow(i, 2), Math.pow(i, 2) % (1L << 32L));
        }
    }

    @Test
    void bigPowTest() {
        assertEquals(power.pow(1, Integer.MAX_VALUE),
                power.pow(1, Integer.MAX_VALUE) % (1L << 32L));
    }
}
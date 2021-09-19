/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 * <p>
 * $Id$
 * Created on Jan 15, 2016
 */

package root.pow;
import java.math.BigInteger;

/**
 * @author Victor Kuliamin
 */
public class Power {
    public long pow(int a, int b) {
        BigInteger r = BigInteger.ONE;
        BigInteger aCopy = BigInteger.valueOf(a);

        while (b > 0) {
            if ((b & 1) != 0) {
                r = r.multiply(aCopy);
            }
            aCopy = aCopy.multiply(aCopy);
            b >>= 1;
        }
        return r.mod(BigInteger.valueOf((1L << 32))).longValue();
    }
}

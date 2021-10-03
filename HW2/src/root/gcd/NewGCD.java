package root.gcd;

public class NewGCD {
    public long gcd(int x, int y) {
        long copyX = x, copyY = y;
        if (copyX < 0) {
            copyX = -copyX;
        }
        if (copyY < 0) {
            copyY = -copyY;
        }
        while (copyY != 0) {
            long t = copyY;
            copyY = copyX % copyY;
            copyX = t;
        }
        return copyX;
    }
}

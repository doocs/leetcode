public class Solution {
    public long SumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;

        while (n > 0) {
            int v = n % 10;
            if (v != 0) {
                s += v;
                x += p * v;
                p *= 10;
            }
            n /= 10;
        }

        return 1L * x * s;
    }
}
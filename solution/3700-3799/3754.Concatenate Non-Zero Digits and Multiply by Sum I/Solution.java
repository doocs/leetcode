class Solution {
    public long sumAndMultiply(int n) {
        int p = 1;
        int x = 0, s = 0;
        for (; n > 0; n /= 10) {
            int v = n % 10;
            s += v;
            if (v != 0) {
                x += p * v;
                p *= 10;
            }
        }
        return 1L * x * s;
    }
}

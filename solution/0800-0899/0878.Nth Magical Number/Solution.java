class Solution {

    public int nthMagicalNumber(int n, int a, int b) {
        long lcm = lcm(a, b);
        long ans = 0;
        // binary search
        for (long l = 0, r = (long) n * Math.min(a, b), m = 0; l <= r;) {
            m = (l + r) / 2;
            if (m / a + m / b - m /lcm >= n) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) (ans % 1000000007);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return (long) a / gcd(a, b) * b;
    }
}

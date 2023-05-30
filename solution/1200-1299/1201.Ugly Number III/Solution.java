class Solution {
    public int nthUglyNumber(int n, int a, int b, int c) {
        long ab = lcm(a, b);
        long bc = lcm(b, c);
        long ac = lcm(a, c);
        long abc = lcm(ab, c);
        long l = 1, r = 2000000000;
        while (l < r) {
            long mid = (l + r) >> 1;
            if (mid / a + mid / b + mid / c - mid / ab - mid / bc - mid / ac + mid / abc >= n) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
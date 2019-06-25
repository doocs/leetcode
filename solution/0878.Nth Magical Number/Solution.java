class Solution {
    public int nthMagicalNumber(int N, int A, int B) {
        long l = 1, r = Long.MAX_VALUE;
        int lcm = A * B / gcd(A, B);
        while (l < r) {
            long mid = l + r >>> 1;
            if (mid / A + mid / B - mid / lcm >= N) r = mid;
            else l = mid + 1;
        }
        return (int) (r % 1000000007);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

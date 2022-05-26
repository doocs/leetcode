class Solution {
    private int mod = 1000000007;

    public int countGoodNumbers(long n) {
        return (int) (myPow(5, (n + 1) >> 1) * myPow(4, n >> 1) % mod);
    }

    private long myPow(long x, long n) {
        long res = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return res;
    }
}
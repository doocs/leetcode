class Solution {
    public int numOfWays(int n) {
        int mod = (int) 1e9 + 7;
        long f0 = 6, f1 = 6;
        for (int i = 0; i < n - 1; ++i) {
            long g0 = (3 * f0 + 2 * f1) % mod;
            long g1 = (2 * f0 + 2 * f1) % mod;
            f0 = g0;
            f1 = g1;
        }
        return (int) (f0 + f1) % mod;
    }
}
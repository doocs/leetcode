class Solution {
    public int monkeyMove(int n) {
        final int mod = (int) 1e9 + 7;
        return (qpow(2, n, mod) - 2 + mod) % mod;
    }

    private int qpow(long a, int n, int mod) {
        long ans = 1;
        for (; n > 0; n >>= 1) {
            if ((n & 1) == 1) {
                ans = ans * a % mod;
            }
            a = a * a % mod;
        }
        return (int) ans;
    }
}
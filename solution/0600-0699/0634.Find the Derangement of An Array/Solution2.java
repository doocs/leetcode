class Solution {
    public int findDerangement(int n) {
        final int mod = (int) 1e9 + 7;
        long a = 1, b = 0;
        for (int i = 2; i <= n; ++i) {
            long c = (i - 1) * (a + b) % mod;
            a = b;
            b = c;
        }
        return (int) b;
    }
}
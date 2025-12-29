class Solution {
    public int sumOfBlocks(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int k = 1;
        for (int i = 1; i <= n; ++i) {
            long x = 1;
            for (int j = k; j < k + i; ++j) {
                x = x * j % mod;
            }
            ans = (ans + x) % mod;
            k += i;
        }
        return (int) ans;
    }
}

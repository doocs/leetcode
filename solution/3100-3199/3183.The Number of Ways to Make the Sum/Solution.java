class Solution {
    public int numberOfWays(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {1, 2, 6};
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = (f[j] + f[j - x]) % mod;
            }
        }
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % mod;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % mod;
        }
        return ans;
    }
}
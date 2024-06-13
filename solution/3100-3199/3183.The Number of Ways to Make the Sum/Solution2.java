class Solution {
    private static final int MOD = 1000000007;
    private static final int M = 100001;
    private static final int[] COINS = {1, 2, 6};
    private static final int[] f = new int[M];
    
    static {
        f[0] = 1;
        for (int x : COINS) {
            for (int j = x; j < M; ++j) {
                f[j] = (f[j] + f[j - x]) % MOD;
            }
        }
    }

    public int numberOfWays(int n) {
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % MOD;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % MOD;
        }
        return ans;
    }
}
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countPartitions(int[] nums, int k) {
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s < k * 2) {
            return 0;
        }
        int n = nums.length;
        long[][] f = new long[n + 1][k];
        f[0][0] = 1;
        long ans = 1;
        for (int i = 1; i <= n; ++i) {
            int v = nums[i - 1];
            ans = ans * 2 % MOD;
            for (int j = 0; j < k; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= v) {
                    f[i][j] = (f[i][j] + f[i - 1][j - v]) % MOD;
                }
            }
        }
        for (int j = 0; j < k; ++j) {
            ans = (ans - f[n][j] * 2 % MOD + MOD) % MOD;
        }
        return (int) ans;
    }
}
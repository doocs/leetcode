class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numRollsToTarget(int n, int k, int target) {
        int[][] f = new int[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(target, i * k); ++j) {
                for (int h = 1; h <= Math.min(j, k); ++h) {
                    f[i][j] = (f[i][j] + f[i - 1][j - h]) % MOD;
                }
            }
        }
        return f[n][target];
    }
}
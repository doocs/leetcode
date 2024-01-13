class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int[] g = new int[target + 1];
            for (int j = 1; j <= Math.min(target, i * k); ++j) {
                for (int h = 1; h <= Math.min(j, k); ++h) {
                    g[j] = (g[j] + f[j - h]) % mod;
                }
            }
            f = g;
        }
        return f[target];
    }
}
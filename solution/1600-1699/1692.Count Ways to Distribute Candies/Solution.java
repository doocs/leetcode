class Solution {
    public int waysToDistribute(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j] = (int) ((long) f[i - 1][j] * j % mod + f[i - 1][j - 1]) % mod;
            }
        }
        return f[n][k];
    }
}
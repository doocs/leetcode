class Solution {
    public int waysToChange(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {25, 10, 5, 1};
        int[][] f = new int[5][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= 4; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = (f[i][j] + f[i][j - coins[i - 1]]) % mod;
                }
            }
        }
        return f[4][n];
    }
}
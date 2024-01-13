class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        final int mod = (int) 1e9 + 7;
        int m = group.length;
        int[][][] f = new int[m + 1][n + 1][minProfit + 1];
        for (int j = 0; j <= n; ++j) {
            f[0][j][0] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                for (int k = 0; k <= minProfit; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        f[i][j][k]
                            = (f[i][j][k]
                                  + f[i - 1][j - group[i - 1]][Math.max(0, k - profit[i - 1])])
                            % mod;
                    }
                }
            }
        }
        return f[m][n][minProfit];
    }
}
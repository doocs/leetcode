class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;
        int[][] f = new int[n + 1][budget + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= budget; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= present[i - 1]) {
                    f[i][j] = Math.max(
                        f[i][j], f[i - 1][j - present[i - 1]] + future[i - 1] - present[i - 1]);
                }
            }
        }
        return f[n][budget];
    }
}
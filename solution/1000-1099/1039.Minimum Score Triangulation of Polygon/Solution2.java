class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] f = new int[n][n];
        for (int i = n - 3; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j]
                        = Math.min(f[i][j], f[i][k] + f[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }
        return f[0][n - 1];
    }
}
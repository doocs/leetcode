class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        int[] dirs = {-2, -1, 2, 1, -2, 1, 2, -1, -2};
        for (int l = 0; l <= k; ++l) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (l == 0) {
                        dp[l][i][j] = 1;
                    } else {
                        for (int d = 0; d < 8; ++d) {
                            int x = i + dirs[d], y = j + dirs[d + 1];
                            if (x >= 0 && x < n && y >= 0 && y < n) {
                                dp[l][i][j] += dp[l - 1][x][y] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }
}